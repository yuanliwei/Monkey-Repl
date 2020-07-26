async function start() {
    let client = await connect()
    client.write('press power\n')
    client.destroy()
}

/**
 * @returns {Promise<import('net').Socket>}
 */
function connect() {
    const net = require('net');
    let HOST = '192.168.1.104'
    let PORT = 5324
    let client = new net.Socket()

    return new Promise((resolve, reject)=> { 
        client.connect(PORT, HOST, () => {
            client.write('!@#$%^&*()\n')
            resolve(client)
        })
        client.on('close', () => {
            console.log('connection closed!');
            reject()
        })
        client.on('error', (data) => {
            console.log('error:' + data);
            reject(data)
        })
    })
}


start()
