fun main(args: Array<String>) {
//    val argss = arrayOf("java kotin", "-cp", "hello", "-class", "clazz" )
    val cmd = Cmd.parseCmd(args)
    if (cmd.versionFlag == true) {
        println("version 0.0.1")
    } else if (cmd.helpFlag == true || cmd.clazz.isNullOrBlank()) {
        Cmd.printUsage()
    } else {
        startJVM()
    }
}

fun startJVM() {
    println("startJvm")
}
