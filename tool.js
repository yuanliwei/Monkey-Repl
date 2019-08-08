// loop Dir Files
let loopDirFiles = (dir, fileArr) => {
    const fs = require('fs')
    const path = require('path')
    fileArr = fileArr || []
    fs.readdirSync(dir).forEach((file) => {
        if ('node_modules' == file) return
        if ('.git' == file) return
        if ('build' == file) return
        var pathname = path.posix.join(dir, file)
        if (fs.statSync(pathname).isDirectory()) {
            loopDirFiles(pathname, fileArr)
        } else {
            if (pathname.endsWith('.java')) {
                fileArr.push(pathname)
            }
        }
    })
    return fileArr
}

// handle file content
let handleFileContent = (filepath) => {
    const fs = require('fs')
    let content = fs.readFileSync(filepath, 'utf-8')

    content = content.replace(/if *\(.*?\) *\{[^\{\}]+?\}/gm, ``)
    content = content.replace(/else *\{[^\{\}]+?\}/gm, ``)
    content = content.replace(/for *\(.*?\) *\{[^\{\}]+?\}/gm, ``)
    content = content.replace(/synchronized *\(.*?\) *\{[^\{\}]+?\}/gm, ``)
    content = content.replace(/switch *\(.*?\) *\{[^\{\}]+?\}/gm, ``)
    content = content.replace(/while *\(.*?\) *\{[^\{\}]+?\}/gm, ``)
    content = content.replace(/private[^\{\}\;]+?;/gm, ``)

    content = content.replace(/try *\{[^\{\}]+\} *catch *\(.*?\) *\{[^\{\}]+\}/gm, ``)

    content = content.replace(/\) *\{[^\{\}]+\}/gm, `) {
        throw new RuntimeException("Stub!");
    }`)

    fs.writeFileSync(filepath, content)
}

function updateSourceList() {
    let files = []
    function handleFileList(filepath) {
        files.push(filepath.replace(/\//g, '/').replace('D:/gitPro/monkey-v2/', ''))
    }
    let dir = 'D:/gitPro/monkey-v2/com/android/commands/monkey'
    loopDirFiles(dir).forEach(file => handleFileList(file))
    const fs = require('fs');
    fs.writeFileSync('D:/gitPro/monkey-v2/source_list', files.join('\n'))
}

let filename = 'D:/node/Monkey-Repl/android/app/ActivityThread.java'
let dir = 'D:/gitPro/monkey-v2/android'

// loopDirFiles(dir).forEach(file => handleFileContent(file))
handleFileContent(filename)
// updateSourceList()