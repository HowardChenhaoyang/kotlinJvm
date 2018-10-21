import classfile.*
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
    println("size is " + result.bytes.size)
    printClassInfo(classFile)
}

fun printClassInfo(classfile: ClassFile) {
    println("version: ${classfile.majorVersion}.${classfile.minorVersion}")
    println("constants count: ${classfile.constantPool.size}")
    println("access flags: ${classfile.accessFlags}")
    println("this class: ${classfile.className()}")
    println("super class: ${classfile.superClassName()}")
    println("interfaces: ${classfile.interfaceNames()}")
    println("fields count: ${classfile.fields?.size ?: 0}")
    if (classfile.fields != null) {
        for (field in classfile.fields!!) {
            println(" ${field.name}")
        }
    }

    println("methods count: ${classfile.methods?.size ?: 0}")
    if (classfile.methods != null) {
        for (method in classfile.methods!!) {
            println(" ${method.name}")
        }
    }
}
