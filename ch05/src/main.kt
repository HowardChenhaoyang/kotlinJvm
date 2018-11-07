import classfile.ClassFile
import classfile.MemberInfo
import classfile.descriptor
import classfile.name
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
    val classFile = ClassFile.parse(result.bytes)
    val mainMethodMemberInfo = getMainMethod(classFile)?:return
    Interpreter.interpret(mainMethodMemberInfo)
}

private fun getMainMethod(classFile: ClassFile): MemberInfo? {
    val methods = classFile.methods ?: return null
    for (method in methods) {
        if (method.name == "main" && method.descriptor == "([Ljava/lang/String;)V") {
            return method
        }
    }
    return null
}