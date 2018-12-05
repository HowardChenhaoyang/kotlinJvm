import classpath.ClassPath
import rtda.heap.ClassLoader

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
    val classPath = ClassPath.parse(cmd)
    val classLoader = ClassLoader.newClassLoader(classPath)
    val className = cmd.clazz!!
    val mainClass = classLoader.loadClass(className)
    val mainMethod = mainClass.getMainMethod()

    if (mainMethod == null){
        println("Main method not found in class $mainClass")
    }else{
        Interpreter.interpret(mainMethod)
    }
}