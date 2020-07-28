const monkey = require('./tool/monkey-net')

let cmds = `
press power
wake
touch down 772 1636
sleep 25
touch move 778 1588
sleep 13
touch move 785 1545
sleep 13
`

async function start() {
    await monkey.connect()
    await monkey.sleep(500)

    cmds = cmds.split('\n').map(o => o.trim()).filter(o => o)

    const sleep = (timeout) => new Promise((resolve) => setTimeout(resolve, timeout))

    for (const cmd of cmds) {
        console.log(cmd);
        await monkey.run(cmd)
    }

    // while (true) {
    // await monkey.run('press power')
    // await monkey.run('press menu')
    // await monkey.sleep(500)
    // await monkey.run('press back')
    // await monkey.sleep(500)

    // - `key down POWER`
    // - `key up POWER`

    // await monkey.run('key down POWER')
    // await monkey.sleep(500)
    // await monkey.run('key up POWER')
    // await monkey.sleep(500)
    //     // let text = await monkey.query('queryview gettree json')
    //     // console.log(text);
    // }
    await monkey.run('quit')
    monkey.close()
}



start()