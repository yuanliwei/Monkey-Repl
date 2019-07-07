const { exec, execSync } = require('child_process');

// execSync('adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar')
let shell = exec('adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"')
shell.stderr.on('data', (chunk) => { console.log(chunk) })
let uuid = () => Math.random().toString().repeat(3)
let run = (command) => { shell.stdin.write(`${command}\n`) }
let sleep = (timeout) => new Promise((resolve) => { setTimeout(() => { resolve() }, timeout) })
let wait = () => new Promise((resolve) => { let unique = uuid(), chunks = []; shell.stdout.removeAllListeners('data'); shell.stdout.on('data', (chunk) => { chunks.push(chunk); if (chunk.includes(unique)) resolve(chunks.join('').replace(unique, '')) }); run(`echo ${unique}`) })
let query = (command) => new Promise(async (resolve) => { await wait(); run(command); resolve((await wait()).replace(/OK:/g, '').trim()) })
let getRect = (name, tree) => tree.split('\n').filter(o => o.includes(name))[0].match(/bounds=\[(\d+),(\d+)\]\[(\d+),(\d+)\]/).slice(1).map(o => parseInt(o))
let getCenter = (rect) => [rect[0] + rect[2], rect[1] + rect[3]].map(o => parseInt(o / 2))
let waitFor = (string) => new Promise(async (resolve) => { let matchLines = []; while (matchLines.length == 0) { let tree = await query('queryview gettree text'); matchLines = tree.split('\n').filter(o => o.includes(string)); await sleep(50) } resolve() })
let clickText = (text) => new Promise(async (resolve) => { await waitFor(text); let tree = await query('queryview gettree text'); run(`tap ${getCenter(getRect(text, tree)).join(' ')}`); await wait(); resolve() })
async function start() {
    // 打开 “Chrome”
    await sleep(100)
    await clickText('Chrome')
    await clickText('百度一下')
    run(`press TAB`)
    run(`press TAB`)
    run(`press TAB`)
    await waitFor('取消')
    run(`type wertyujbvcdf`)
    run(`press ENTER`)
    run('quit')
}

start()