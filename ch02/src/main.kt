import classpath.ClassPath

fun main(args: Array<String>) {
    val cmd = Cmd.parseCmd(args)
    if (cmd.versionFlag == true) {
        println("version 0.0.1")
    } else if (cmd.helpFlag == true || cmd.clazz.isNullOrBlank()) {
        Cmd.printUsage()
    } else {
        startJVM(cmd)
    }
}

fun startJVM(cmd: Cmd) {
    val result = ClassPath.parse(cmd).readClass(cmd.clazz!!)
    println("size is "+ result.bytes.size)
    println(result.bytes.toList())
}
