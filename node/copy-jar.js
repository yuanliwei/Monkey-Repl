/**
 * 把编译产物 dist/monkey_repl.jar 复制到 resource/monkey_repl.jar
 * （编译产物不进 git，需要发布时才执行此脚本同步）
 */

import { copyFileSync, existsSync } from 'node:fs'
import { fileURLToPath } from 'node:url'
import path from 'node:path'

/** 项目根目录 */
const rootDir = fileURLToPath(new URL('../', import.meta.url))
const source = path.join(rootDir, 'dist/monkey_repl.jar')
const target = path.join(rootDir, 'resource/monkey_repl.jar')

if (!existsSync(source)) {
    console.error(`未找到编译产物：${source}\n请先运行 npm run build`)
    process.exit(1)
}

copyFileSync(source, target)
console.log(`copied: ${source}\n     -> ${target}`)
