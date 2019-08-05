const { exec, execSync } = require('child_process');

// execSync('adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar')
let shell = exec('adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"')
shell.stderr.on('data', (chunk) => { console.log(chunk) })
let uuid = () => Math.random().toString().repeat(3)
let run = (command) => shell.stdin.write(`${command}\n`)
let sleep = (timeout) => new Promise((resolve) => setTimeout(resolve, timeout))
let wait = () => new Promise((resolve) => { let unique = uuid(), chunks = []; shell.stdout.removeAllListeners('data'); shell.stdout.on('data', (chunk) => { chunks.push(chunk); if (chunk.includes(unique)) resolve(chunks.join('').replace(unique, '')) }); run(`echo ${unique}`) })
let query = (command) => new Promise(async (resolve) => { await wait(); run(command); resolve((await wait()).replace(/OK:/g, '').trim()) })
let getRect = (bounds) => bounds.match(/\[(-?\d+),(-?\d+)\]\[(-?\d+),(-?\d+)\]/).slice(1).map(o => parseInt(o))
let filterVisible = (tree) => { let loop = (o, w, h, gap) => o.childrens = o.childrens.filter(n => { loop(n, w, h, gap); let r = getRect(n.bounds); return n.childrens.length > 0 || (r[0] < w - gap && r[1] < h - gap && r[2] > gap && r[3] > gap && r[2] - r[0] > gap && r[3] - r[1] > 5) }); let rect = getRect(tree.bounds); loop(tree, rect[2], rect[3], 5); return tree }
let getCenter = (rect) => [rect[0] + rect[2], rect[1] + rect[3]].map(o => parseInt(o / 2))
let clickCenter = (center) => run(`tap ${center.join(' ')}`)
let clickRect = (rect) => clickCenter(getCenter(rect))
let waitChange = () => new Promise(async (resolve) => { while ((await query('getisviewchange')) == 'false') sleep(10); resolve() })
let getVisibleTree = async () => filterVisible(JSON.parse(await query('queryview gettree json')))
let waitTreeFor = (string) => new Promise(async (resolve) => { while (true) { let tree = await getVisibleTree(); if (JSON.stringify(tree).includes(string)) { resolve(tree); break } await waitChange() } })
let findInTree = (tree, match) => { let obj; let loop = (child) => { if (!obj && match(child)) obj = child; else child.childrens.forEach(loop) }; loop(tree); return obj }
let clickAny = async (tree, match) => clickRect(getRect(findInTree(tree, match).bounds))
let clickText = async (tree, text) => clickAny(tree, o => o.text && o.text.includes(text))
let clickId = async (tree, resourceId) => clickAny(tree, o => o.resource_id_name == resourceId)

async function start() {
    await sleep(100)
    // let location = await query('queryview getlocation')
    // console.log('getlocation : |', location, '|');
    // let screenshot = await query('takescreenshot scale 0.3')
    // screenshot = screenshot.replace(/[\r|\n]/g, '')
    // console.log('screenshot', 'data:image/jpeg;base64,' + screenshot);
    // while(true){
    //     let s = await query('getisviewchange')
    //     console.log(`query('queryview ischange = ')`,s);
    // }

    let top = await query('gettopactivity')
    console.log('top : ', top);
    

    // let tree = await waitTreeFor("话题")
    // await clickText(tree, '话题')
    console.log('ok');
    // let obj = findInTree(tree, o=>o.text&&o.text.includes('话题'))
    // console.log(obj);


    run('quit')
}

start()