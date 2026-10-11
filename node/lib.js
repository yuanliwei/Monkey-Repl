import { spawn, execSync } from 'child_process'
import { fileURLToPath } from 'node:url'
import { existsSync } from 'node:fs'

/**
 * @import {ViewTree, Options, MatchCallback, ExecResult} from './types.js'
 */

export class Controller {

    /**
     * @param {Options} options 
     */
    constructor(options) {
        this.sequenceId = 123

        /** @type{Options} */
        this.options = Object.assign({
            type: 'repl',
            command_type: 'json',
            name: 'monkey-repl',
            port: 5678,
            ip_address: '',
            allow_ip_address: '192.168.*',
            query_view: true,
            activity_controller: true,
        }, options)


        /** @type {(command: { id: number, data: string }) => Promise<any>} */
        this.execImpl = async (command) => { console.log(command) }
    }

    async connect() {
        const { type, command_type, name, port, allow_ip_address, query_view, activity_controller } = this.options
        if (type == 'repl') {
            let devicesStr = execSync(`adb devices`).toString()
            let devices = devicesStr.split('\n').map(o => o.trim()).filter(o => o)
            devices = devices.map(o => o.split(/\s+/)).filter(o => o.length == 2).map(o => o[0])
            console.log(devices)
            if (!devices.length) throw Error('没有连接的手机')
            let device = devices.shift()
            // 推送 monkey_repl.jar 到设备
            if (!this.options.skipPush) {
                const distJar = fileURLToPath(new URL('../dist/monkey_repl.jar', import.meta.url))
                const resourceJar = fileURLToPath(new URL('../resource/monkey_repl.jar', import.meta.url))
                const jarPath = existsSync(distJar) ? distJar : resourceJar
                execSync(`adb -s ${device} push "${jarPath}" /data/local/tmp/monkey_repl.jar`)
            }
            const queryCallbackMap = {}
            const shell = spawn(`adb`, [`-s`, `${device}`, `shell`])
            shell.stderr.setEncoding('utf-8')
            shell.stdout.setEncoding('utf-8')
            shell.stderr.on('data', (chunk) => { if (this.options.debug) console.error('err', chunk) })
            // shell.stdin.on('error', (chunk) => { console.error('error', chunk) })
            // shell.stdout.on('error', (chunk) => { console.error('error', chunk) })
            // shell.stderr.on('error', (chunk) => { console.error('error', chunk) })
            // shell.stdin.on('close', (chunk) => { console.error('close', chunk) })
            // shell.stdout.on('close', (chunk) => { console.error('close', chunk) })
            // shell.stderr.on('close', (chunk) => { console.error('close', chunk) })

            shell.stdin.write(`export CLASSPATH=/data/local/tmp/monkey_repl.jar\n`)
            shell.stdin.write(`exec app_process /system/bin com.android.commands.monkey.Monkey --type ${type} --command_type ${command_type} --name ${name} --port ${port} --allow_ip_address ${allow_ip_address} --query_view ${query_view} --activity_controller ${activity_controller}\n`)

            let buffer = ''
            shell.stdout.on('data', (chunk) => {
                buffer += chunk
                let lines = buffer.split('\n')
                buffer = lines.pop()  // 最后一段可能不完整，留到下个 chunk
                for (const line of lines) {
                    let text = line.trim()
                    if (!text) continue
                    try {
                        let o = JSON.parse(text)
                        let promiseResolve = queryCallbackMap[o.id]
                        if (promiseResolve) {
                            promiseResolve(o)
                            delete queryCallbackMap[o.id]
                        }
                    } catch (error) {
                        // 非 JSON 行（日志残留等）忽略
                    }
                }
            })

            this.execImpl = async (command) => new Promise((resolve) => {
                // console.log(command)
                queryCallbackMap[command.id] = resolve
                shell.stdin.write(JSON.stringify(command) + '\n')
            })
        }

        if (type == 'network') {
            // curl http://ip_address:5678 -d help
            this.execImpl = async (command) => /** @type {any} */ (await (await fetch(`http://${this.options.ip_address}:${this.options.port}`, { method: 'POST', body: JSON.stringify(command) })).json())
        }

        let result = await this.exec(`echo test`)

        console.log('connect success! ', result)
    }

    /**
     * @param {String} command 
     * @returns {Promise<ExecResult>}
     */
    async exec(command) {
        let id = this.sequenceId++
        return this.execImpl({ id, data: command })
    }

    /**
     * @param {number} timeout sleep timeout millisecond
     */
    async sleep(timeout) { return new Promise((resolve) => setTimeout(resolve, timeout)) }


    /**
     * run command and return result
     *
     * @param {string} command run command
     *
     * @returns {Promise<Object>}
     */
    async query(command) {
        return this.exec(command)
    }

    async quit() {
        return this.exec('quit')
    }

    async press(key) {
        return this.exec(`press ${key}`)
    }

    /**
     * @param {Number} x 
     * @param {Number} y 
     */
    async tap(x, y) {
        x = Math.round(x)
        y = Math.round(y)
        return this.exec(`tap ${x} ${y}`)
    }

    /**
     * @param {Number} x 
     * @param {Number} y 
     */
    async touchDown(x, y) {
        x = Math.round(x)
        y = Math.round(y)
        return this.exec(`touch down ${x} ${y}`)
    }

    /**
     * @param {Number} x 
     * @param {Number} y 
     */
    async touchMove(x, y) {
        x = Math.round(x)
        y = Math.round(y)
        return this.exec(`touch move ${x} ${y}`)
    }

    /**
     * @param {Number} x 
     * @param {Number} y 
     */
    async touchUp(x, y) {
        x = Math.round(x)
        y = Math.round(y)
        return this.exec(`touch up ${x} ${y}`)
    }

    /**
     * 滑动
     * @param {Number} x1 
     * @param {Number} y1 
     * @param {Number} x2 
     * @param {Number} y2 
     * @param {Number} time 
     * @param {Number} step 
     */
    async slide(x1, y1, x2, y2, time, step) {
        // slide x1 y1 x2 y2 time step
        x1 = Math.round(x1)
        y1 = Math.round(y1)
        x2 = Math.round(x2)
        y2 = Math.round(y2)
        time = Math.round(time)
        step = Math.round(step)
        return this.exec(`slide ${x1} ${y1} ${x2} ${y2} ${time} ${step}`)
    }

    /**
     * @param {String} text 
     */
    async type(text) {
        await this.exec(`copy base64 ${Buffer.from(text).toString('base64')}`)
        return this.exec(`press KEYCODE_PASTE`)
    }

    /**
     * get rect from bounds string
     *
     * @param {string} bounds arrar number string
     * @returns {[number, number, number,number]} rect [left, top, right, bottom]
     */
    getRect(bounds) {
        let match = bounds.match(/\[(-?\d+),(-?\d+)\]\[(-?\d+),(-?\d+)\]/)
        if (!match) throw new Error(`invalid bounds: ${bounds}`)
        let [, left, top, right, bottom] = match
        return /** @type {[number, number, number, number]} */ ([parseInt(left), parseInt(top), parseInt(right), parseInt(bottom)])
    }

    /**
     * delete invisible tree nodes
     *
     * @param {ViewTree} tree a json view tree
     * @returns {ViewTree}
     */
    filterVisible(tree) {
        let loop = (o, w, h, gap) => o.childrens = o.childrens.filter(n => {
            loop(n, w, h, gap)
            let r = this.getRect(n.bounds)
            return n.childrens.length > 0
                || (r[0] < w - gap
                    && r[1] < h - gap
                    && r[2] > gap
                    && r[3] > gap
                    && r[2] - r[0] > gap
                    && r[3] - r[1] > 5)
        })
        let rect = this.getRect(tree.bounds)
        loop(tree, rect[2], rect[3], 5)
        return tree
    }

    /**
     * get center point in rect
     *
     * @param {number[]} rect rect [left, top, right, bottom]
     * @returns {number[]} [x, y]
     */
    getCenter(rect) {
        return [rect[0] + rect[2], rect[1] + rect[3]].map(o => Math.round(o / 2))
    }

    /**
     * click at point
     *
     * @param {number[]} center [x, y]
     */
    async clickCenter(center) {
        return this.exec(`tap ${center.join(' ')}`)
    }

    /**
     * click rect center
     *
     * @param {number[]} rect [left, top, right, bottom]
     */
    async clickRect(rect) {
        return this.clickCenter(this.getCenter(rect))
    }

    /**
     * wait a change on mobile screen
     */
    async waitChange() {
        let count = 0
        while ((await this.exec('getisviewchange')).data == 'false' && count++ < 50) {
            await this.sleep(10)
        }
    }

    /**
     * get json tree only visible
     *
     * @returns {Promise<ViewTree>}
     */
    async getVisibleViewTree() {
        let ret = await this.exec('queryview gettree json')
        if (!ret.isSuccess) {
            ret = await this.exec('queryview gettree json')
        }
        if (!ret.isSuccess) {
            console.error(ret)
            return /** @type {ViewTree} */ ({})
        }
        try {
            let tree = JSON.parse(ret.data)
            return this.filterVisible(tree)
        } catch (e) {
            console.log(ret)
            throw new Error(typeof ret.data === 'string' ? ret.data : JSON.stringify(ret))
        }
    }

    /**
     * wait until screen includes the string
     *
     * @param {string} string string
     */
    async waitTreeFor(string) {
        let status = true
        while (status) {
            let tree = await this.getVisibleViewTree()
            if (JSON.stringify(tree).includes(string)) {
                return tree
            }
            await this.waitChange()
        }
    }

    /**
     * wait until screen includes the view
     *
     * @param {MatchCallback} match
     * @returns {Promise<ViewTree>}
     */
    async waitViewForMatch(match) {
        let status = true
        while (status) {
            let tree = await this.getVisibleViewTree()
            let view = this.findViewInTree(tree, match)
            if (view) {
                return view
            }
            await this.waitChange()
        }
    }

    /**
     * find view in viewtree with match
     *
     * @param {ViewTree} tree
     * @param {MatchCallback} match
     * 
     * @returns {ViewTree[]}
     */
    findViewsInTree(tree, match) {
        let objs = /** @type {ViewTree[]} */ ([])
        let loop = (child) => {
            if (match(child)) {
                objs.push(child)
            } else {
                child.childrens.forEach(loop)
            }
        }
        loop(tree)
        return objs
    }

    /**
     * find view in viewtree with match
     *
     * @param {ViewTree} tree
     * @param {MatchCallback} match
     * 
     * @returns {ViewTree}
     */
    findViewInTree(tree, match) {
        return this.findViewsInTree(tree, match)[0]
    }

    /**
     * click view that match MatchCallback
     *
     * @param {ViewTree} view
     */
    async clickView(view) {
        return this.clickRect(this.getRect(view.bounds))
    }

    /**
     * click view that match MatchCallback
     *
     * @param {ViewTree} tree
     * @param {MatchCallback} match
     */
    async clickMatchView(tree, match) {
        let view = this.findViewInTree(tree, match)
        return this.clickView(view)
    }

    /**
     * click view that view text includes text
     *
     * @param {ViewTree} tree
     * @param {string} text
     */
    async clickText(tree, text) {
        return this.clickMatchView(tree, o => o.text && o.text.includes(text))
    }

    /**
     * click view that view resourceId is resourceId
     *
     * @param {ViewTree} tree
     * @param {string} resourceId
     */
    async clickId(tree, resourceId) {
        return this.clickMatchView(tree, o => o.resource_id_name == resourceId)
    }

}

export default Controller
