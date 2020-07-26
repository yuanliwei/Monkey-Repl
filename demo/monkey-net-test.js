const monkey = require('./tool/monkey-net')

async function start() {
    await monkey.sleep(500)
    while (true) {
        await monkey.run('press menu')
        await monkey.sleep(500)
        await monkey.run('press back')
        await monkey.sleep(500)
        // let text = await monkey.query('queryview gettree json')
        // console.log(text);
    }
    await monkey.run('quit')
    console.log('iop');
}



start()