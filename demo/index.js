const { exec, execSync } = require('child_process');

// execSync('adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar')
let shell = exec('adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"')
shell.stderr.on('data', (chunk) => { console.log(chunk) })
let uuid = () => Math.random().toString().repeat(3)
let run = (command) => { shell.stdin.write(`${command}\n`) }
let sleep = (timeout) => new Promise((resolve) => { setTimeout(() => { resolve() }, timeout) })
let wait = () => new Promise((resolve) => { let unique = uuid(), chunks = []; shell.stdout.removeAllListeners('data'); shell.stdout.on('data', (chunk) => { chunks.push(chunk); if (chunk.includes(unique)) resolve(chunks.join('').replace(unique, '')) }); run(`echo ${unique}`) })
let query = (command) => new Promise(async (resolve) => { await wait(); run(command); resolve((await wait()).replace(/OK:/g, '').trim()) })
let getRectInLine = (line) => line.match(/bounds=\[(\d+),(\d+)\]\[(\d+),(\d+)\]/).slice(1).map(o => parseInt(o))
let getRect = (name, tree) => getRectInLine(tree.split('\n').filter(o => o.includes(name))[0])
let getCenter = (rect) => [rect[0] + rect[2], rect[1] + rect[3]].map(o => parseInt(o / 2))
let waitFor = (string) => new Promise(async (resolve) => { let matchLines = []; while (matchLines.length == 0) { let tree = await query('queryview gettree text'); matchLines = tree.split('\n').filter(o => o.includes(string)); await sleep(50) } resolve() })
let clickCenter = (center) => run(`tap ${center.join(' ')}`)
let clickRect = (rect) => clickCenter(getCenter(rect))
let clickText = (text) => new Promise(async (resolve) => { await waitFor(text); let tree = await query('queryview gettree text'); clickRect(getRect(text, tree)); await wait(); resolve() })
let findEditText = () => new Promise(async (resolve) => { let tree = await query(`queryview gettree text`); let rects = tree.split('\n').filter(o => o.includes('class=android.widget.EditText')).map(o => getRectInLine(o)); resolve(rects) })

async function start() {
    await sleep(1000)
    let location = await query('queryview getlocation')
    console.log('getlocation : |', location, '|');
    let screenshot = await query('takescreenshot scale 0.3')
    screenshot = screenshot.replace(/[\r|\n]/g, '')
    console.log('screenshot', 'data:image/jpeg;base64,' + screenshot);
    run('quit')
}

start()