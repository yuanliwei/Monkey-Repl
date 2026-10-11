/**
 * monkey-repl 编译打包脚本
 * 流程：javac → jar → d8 → aapt（把 dex 打进 jar）
 * @import {ExecSyncOptions} from 'node:child_process'
 */

import { execSync } from 'node:child_process'
import { rmSync, mkdirSync, cpSync } from 'node:fs'
import { fileURLToPath } from 'node:url'
import path from 'node:path'

/** 项目根目录 */
const rootDir = fileURLToPath(new URL('../', import.meta.url))
/** Java 源码目录 */
const javaDir = path.join(rootDir, 'java')
/** 资源目录 */
const resourceDir = path.join(rootDir, 'resource')
/** 产物目录 */
const distDir = path.join(rootDir, 'dist')
/** 编译中间产物目录 */
const buildDir = path.join(distDir, 'build')

/** Android build-tools 目录（可用 BUILD_TOOLS_DIR 环境变量覆盖） */
const buildToolsDir = process.env.BUILD_TOOLS_DIR || 'D:/Android/sdk/build-tools/36.1.0'
const d8 = path.join(buildToolsDir, 'd8.bat')
const aapt = path.join(buildToolsDir, 'aapt.exe')

/** 编译中间 jar */
const javaBuildJar = path.join(distDir, 'java_build.jar')
/** 生成的 dex */
const classesDex = path.join(distDir, 'classes.dex')
/** 最终产物 jar */
const monkeyReplJar = path.join(distDir, 'monkey_repl.jar')
/** 源文件列表 */
const sourceList = path.join(resourceDir, 'source_list')
/** manifest */
const manifest = path.join(resourceDir, 'MANIFEST.MF')

/**
 * 执行命令（继承 stdio，输出到终端）
 * @param {string} command 命令
 * @param {string} [cwd] 工作目录
 */
function run(command, cwd = rootDir) {
    console.log(`\n> ${command}`)
    execSync(command, { stdio: 'inherit', cwd })
}

// 1. 清理中间产物
rmSync(buildDir, { recursive: true, force: true })
mkdirSync(buildDir, { recursive: true })

// 2. 编译 Java（--release 8 保证 class 版本兼容 d8）
run(`javac --release 8 -g -encoding UTF-8 -sourcepath "${javaDir}" -d "${buildDir}" "@${sourceList}"`)

// 3. 打包成 jar（只含 com/ 包）
run(`jar -cfm "${javaBuildJar}" "${manifest}" -C "${buildDir}" com/`)

// 4. 转 dex（d8）
run(`"${d8}" --output "${distDir}" "${javaBuildJar}"`)

// 5. 把 dex 加进 jar（aapt 需在产物目录运行，保证 dex 路径为 classes.dex）
rmSync(monkeyReplJar, { force: true })
run(`"${aapt}" add "${monkeyReplJar}" classes.dex`, distDir)

console.log(`\nbuild done: ${monkeyReplJar}`)
