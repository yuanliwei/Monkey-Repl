// adb shell getevent -ltr

let rawEvents = `
PS C:\Users\y> adb shell getevent -ltr
add device 1: /dev/input/event7
  name:     "msm8952-cdp-snd-card Headset Jack"
add device 2: /dev/input/event6
  name:     "msm8952-cdp-snd-card Button Jack"
add device 3: /dev/input/event5
  name:     "goodixfp"
add device 4: /dev/input/event3
  name:     "qpnp_pon"
could not get driver version for /dev/input/mouse0, Not a typewriter
add device 5: /dev/input/event2
  name:     "hbtp_vm"
add device 6: /dev/input/event1
  name:     "input_mt_wrapper"
could not get driver version for /dev/input/mice, Not a typewriter
add device 7: /dev/input/event4
  name:     "gpio-keys"
add device 8: /dev/input/event0
  name:     "synaptics_dsx_i2c"
[  397653.503507] /dev/input/event0: EV_ABS       ABS_MT_TRACKING_ID   0000340f
[  397653.503507] /dev/input/event0: EV_KEY       BTN_TOUCH            DOWN
[  397653.503507] /dev/input/event0: EV_KEY       BTN_TOOL_FINGER      DOWN
[  397653.503507] /dev/input/event0: EV_ABS       ABS_MT_POSITION_X    00000304
[  397653.503507] /dev/input/event0: EV_ABS       ABS_MT_POSITION_Y    00000664
[  397653.503507] /dev/input/event0: EV_ABS       ABS_MT_TOUCH_MAJOR   00000006
[  397653.503507] /dev/input/event0: EV_SYN       SYN_REPORT           00000000
[  397653.529221] /dev/input/event0: EV_ABS       ABS_MT_POSITION_X    0000030a
[  397653.529221] /dev/input/event0: EV_ABS       ABS_MT_POSITION_Y    00000634

`

async function start() {
    let lines = rawEvents.split('\n').filter(o => o.startsWith('[')).map(o => {
        let [_, time, device, type, name, action] = o.trim().match(/\[ *([^ ]+)\] +([^:])+: ([^ ]+) +([^ ]+) +([^ ]+)/)
        return { time, device, type, name, action };
    })
    let events = []
    let records = []
    lines.forEach(o => {
        records.push(o)
        if (o.type == 'EV_SYN') {
            events.push(records)
            records = []
        }
    })

    let tmp = []
    events.forEach(arr => {
        convertEvent(arr, tmp)
    })

    console.log(tmp.join('\n'));
}

let lastTime = 0;
let downTime;
let downX;
let downY;
let touchX;
let touchY;
let lastX;
let lastY;

function addSleep(time, tmp) {
    let currentTime = parseFloat(time)
    if (lastTime != 0) {
        tmp.push(`sleep ${parseInt((currentTime - lastTime) * 1000)}`)
    }
    lastTime = currentTime
}

function recordPosition(x, y) {
    lastX = x
    lastY = y
}

/**
 * 
 * @param {Object[]} r 
 */
function convertEvent(r, tmp) {
    let types = r.map(o => o.type).join(' ')
    let names = r.map(o => o.name).join(' ')
    switch (types) {
        case 'EV_KEY EV_SYN': // press [up|down] key
            addSleep(r[1].time, tmp)
            let keyCode = r[0].name.match(/[0-9a-f]/) ? parseInt(r[0].name) : r[0].name
            keyCode = `${keyCode}`.includes('_') ? keyCode.split('_')[1] : keyCode
            tmp.push(`key ${r[0].action.toLowerCase()} ${keyCode}`)
            break
        case 'EV_ABS EV_KEY EV_KEY EV_ABS EV_ABS EV_SYN':// touch down
            downTime = parseFloat(r[5].time)
            touchX = parseInt(r[3].action, 16)
            touchY = parseInt(r[4].action, 16)
            downX = touchX
            downY = touchY
            recordPosition(touchX, touchY)
            addSleep(r[5].time, tmp)
            tmp.push(`touch ${r[2].action.toLowerCase()} ${touchX} ${touchY}`)
            break
        case 'EV_ABS EV_KEY EV_KEY EV_ABS EV_ABS EV_ABS EV_SYN': // touch down
            downTime = parseFloat(r[6].time)
            touchX = parseInt(r[3].action, 16)
            touchY = parseInt(r[4].action, 16)
            recordPosition(touchX, touchY)
            addSleep(r[6].time, tmp)
            tmp.push(`touch ${r[2].action.toLowerCase()} ${touchX} ${touchY}`)
            break
        case 'EV_ABS EV_KEY EV_KEY EV_ABS EV_ABS EV_ABS EV_ABS EV_SYN': // touch down
            downTime = parseFloat(r[7].time)
            touchX = parseInt(r[3].action, 16)
            touchY = parseInt(r[4].action, 16)
            recordPosition(touchX, touchY)
            addSleep(r[7].time, tmp)
            tmp.push(`touch ${r[2].action.toLowerCase()} ${touchX} ${touchY}`)
            break
        case 'EV_ABS EV_KEY EV_KEY EV_SYN': // touch up
            addSleep(r[3].time, tmp)
            tmp.push(`touch ${r[2].action.toLowerCase()} ${lastX} ${lastY}`)
            break
        default:
            switch (names) {
                case 'ABS_MT_TOUCH_MINOR SYN_REPORT': // EV_ABS EV_SYN
                case 'ABS_MT_TOUCH_MAJOR SYN_REPORT': // EV_ABS EV_SYN
                case 'ABS_MT_TOUCH_MAJOR ABS_MT_TOUCH_MINOR SYN_REPORT': // EV_ABS EV_ABS EV_SYN

                    break;
                case 'ABS_MT_POSITION_X ABS_MT_POSITION_Y ABS_MT_TOUCH_MAJOR SYN_REPORT': // touch move
                case 'ABS_MT_POSITION_X ABS_MT_POSITION_Y ABS_MT_TOUCH_MINOR SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    touchY = parseInt(r[1].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[3].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_X ABS_MT_POSITION_Y ABS_MT_TOUCH_MAJOR ABS_MT_TOUCH_MINOR SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    touchY = parseInt(r[1].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[4].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_X ABS_MT_POSITION_Y SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    touchY = parseInt(r[1].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[2].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_Y SYN_REPORT': // touch move
                    touchY = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[1].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_Y ABS_MT_TOUCH_MINOR SYN_REPORT': // touch move
                    touchY = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[2].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_X ABS_MT_TOUCH_MINOR SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[2].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_Y ABS_MT_TOUCH_MAJOR SYN_REPORT': // touch move
                    touchY = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[2].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_X ABS_MT_TOUCH_MINOR ABS_MT_TOUCH_MAJOR SYN_REPORT': // touch move
                case 'ABS_MT_POSITION_X ABS_MT_TOUCH_MAJOR ABS_MT_TOUCH_MINOR SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[3].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_Y ABS_MT_TOUCH_MINOR ABS_MT_TOUCH_MAJOR SYN_REPORT': // touch move
                case 'ABS_MT_POSITION_Y ABS_MT_TOUCH_MAJOR ABS_MT_TOUCH_MINOR SYN_REPORT': // touch move
                    touchY = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[3].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_X ABS_MT_TOUCH_MAJOR SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[3].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;
                case 'ABS_MT_POSITION_X SYN_REPORT': // touch move
                    touchX = parseInt(r[0].action, 16)
                    recordPosition(touchX, touchY)
                    addSleep(r[1].time, tmp)
                    tmp.push(`touch move ${touchX} ${touchY}`)
                    break;

                default:
                    console.log('types :', types);
                    console.log('names :', names);
                    console.log(r);
                    throw new Error(JSON.stringify(r))
            }
    }
}

start()