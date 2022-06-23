import Controller from "android-controller-wrapper"

async function start() {
    let controller = new Controller({})
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

    await controller.quit()
    console.log('over!')

}

start()