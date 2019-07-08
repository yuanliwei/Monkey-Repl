var PORT = 18907;

var http = require('http');
var url = require('url');
const fs = require('fs');
const path = require('path');

const { exec, execSync } = require('child_process');

// execSync('adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar')
let shell = exec('adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"')
shell.stderr.on('data', (chunk) => { console.log(chunk) })
let uuid = () => Math.random().toString().repeat(3)
let run = (command) => { shell.stdin.write(`${command}\n`) }
let sleep = (timeout) => new Promise((resolve) => { setTimeout(() => { resolve() }, timeout) })
let wait = () => new Promise((resolve) => {
    let unique = uuid(),
        chunks = [];
    shell.stdout.removeAllListeners('data');
    shell.stdout.on('data', (chunk) => {
        // console.log("CHUNK:",chunk);
        // if (!chunk.startsWith('OK:')) return;
        chunks.push(chunk);
        if (chunk.includes(unique)) resolve(chunks.join('').replace(unique, ''))
    });
    run(`echo ${unique}`)
})
let query = (command) => new Promise(async (resolve) => {
    await wait();
    run(command);
    resolve((await wait()).replace(/OK:/g, '').trim())
})
let getRectInLine = (line) => line.match(/bounds=\[(\d+),(\d+)\]\[(\d+),(\d+)\]/).slice(1).map(o => parseInt(o))
let getRect = (name, tree) => getRectInLine(tree.split('\n').filter(o => o.includes(name))[0])
let getCenter = (rect) => [rect[0] + rect[2], rect[1] + rect[3]].map(o => parseInt(o / 2))
let waitFor = (string) => new Promise(async (resolve) => { let matchLines = []; while (matchLines.length == 0) { let tree = await query('queryview gettree text'); matchLines = tree.split('\n').filter(o => o.includes(string)); await sleep(50) } resolve() })
let waitForColor = (color, x, y) => new Promise(async (resolve) => { let screenColor = ''; while (screenColor != color) { screenColor = await query(`takescreenshot getcolor ${x} ${y}`); await sleep(50) } resolve() })
let clickCenter = (center) => run(`tap ${center.join(' ')}`)
let clickRect = (rect) => clickCenter(getCenter(rect))
let clickText = (text) => new Promise(async (resolve) => { await waitFor(text); let tree = await query('queryview gettree text'); clickRect(getRect(text, tree)); await wait(); resolve() })
let findEditText = () => new Promise(async (resolve) => { let tree = await query(`queryview gettree text`); let rects = tree.split('\n').filter(o => o.includes('class=android.widget.EditText')).map(o => getRectInLine(o)); resolve(rects) })

var server = http.createServer(async (request, response) => {
    try {
        var pathname = url.parse(request.url).pathname;
        console.log('pathname:', pathname);
        switch (pathname) {
            case '/takescreenshot': return await takescreenshot(request, response)
            case '/listviewtree': return await listviewtree(request, response)
            default: return sendFile(request, response)
        }
    } catch (error) {
        HTTP500(error, response)
    }
});

function sendFile(request, response) {
    var pathname = url.parse(request.url).pathname;
    pathname = pathname == '/' ? '/index.html' : pathname
    var realPath = path.join(__dirname, pathname);
    let exists = fs.existsSync(realPath)
    if (!exists) return HTTP404(request, response)
    response.writeHead(200, { 'Content-Type': 'text/html' });
    let content = fs.readFileSync(realPath, 'binary')
    response.write(content, "binary");
    response.end();
}

function HTTP500(error, resp) {
    if (!error) return false;
    console.error(error);
    resp.writeHead(500, { 'Content-Type': 'text/plain' });
    resp.write('error:' + error.stack);
    resp.end();
    return error;
}

function HTTP404(req, resp) {
    resp.writeHead(404, { 'Content-Type': 'text/plain' });
    let message = "This request URL " + req.url + " was not found on this server."
    console.error(message);
    resp.write(message);
    resp.end();
}

server.listen(PORT);
console.log("Server runing at port: " + PORT + ".");

async function takescreenshot(req, resp) {
    let screenshot = await query('takescreenshot scale 0.8')
    screenshot = screenshot.replace(/[\r|\n]/g, '')
    resp.writeHead(200, { 'Content-Type': 'image/jpeg' });
    resp.write(`data:image/jpeg;base64,${screenshot}`);
    resp.end();
}
async function listviewtree(req, resp) {
    let tree = await query('queryview gettree json')
    resp.writeHead(200, { 'Content-Type': 'application/json' });
    resp.write(tree);
    resp.end();
}

// exec(`start http://127.0.0.1:${PORT}`);
