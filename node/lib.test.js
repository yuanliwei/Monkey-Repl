import { test } from 'node:test'
import assert from 'node:assert'
import { Controller } from './lib.js'

test('android controller flow', async () => {
    await using controller = new Controller({
        // activity_controller: false,
        // query_view: false,
        // type: 'tcp',
        // ip_address:'192.168.40.86'
    })
    await controller.connect()

    await controller.slide(200, 500, 1000, 600, 1000, 100)
    await controller.slide(1000, 500, 200, 600, 1000, 100)

    let tree = await controller.getVisibleViewTree()
    await controller.clickText(tree, '便签')
    await controller.sleep(1000)

    await controller.tap(540, 2200)
    await controller.sleep(300)

    await controller.exec('press a')
    await controller.sleep(300)

    await controller.exec('press b')
    await controller.sleep(300)

    await controller.press('c')
    await controller.sleep(300)

    await controller.press('d')
    await controller.sleep(300)

    await controller.press('ENTER')
    await controller.sleep(300)

    await controller.type('福满须防有祸，凶多料必无争。')

    await controller.press(`ENTER`)
    await controller.sleep(300)

    await controller.press(`BACK`)
    await controller.sleep(300)
    await controller.press(`BACK`)
    await controller.sleep(300)

    let r = 500
    let centerX = 20 + r
    let centerY = 2 * r
    await controller.touchDown(centerX, centerY + r)

    for (let i = 0; i < 2 * Math.PI; i += 0.01) {
        let x = centerX + Math.sin(i) * r
        let y = centerY + Math.cos(i) * r
        await controller.touchMove(x, y)
        await controller.sleep(10)
    }
    await controller.touchUp(centerX, centerY + r)
    await controller.sleep(300)

    await controller.press(`HOME`)
    await controller.sleep(300)

    console.log('over!')

})

test('get screen size', async () => {
    await using controller = new Controller({
        // activity_controller: false,
        // query_view: false,
        // type: 'tcp',
        // ip_address:'192.168.40.86'
    })
    await controller.connect()

    let result = await controller.svc('display', 'info')
    console.log('display info: ', result)

    let info = JSON.parse(result.data)
    let width = info.logicalWidth
    let height = info.logicalHeight
    console.log(`screen size: ${width}x${height}`)

    assert.ok(Number.isInteger(width) && width > 0, 'width should be positive integer')
    assert.ok(Number.isInteger(height) && height > 0, 'height should be positive integer')

})

