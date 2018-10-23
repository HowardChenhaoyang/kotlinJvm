import rtda.*

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
    val frame = Frame.newFrame(100, 100)
//    testLocalVars(frame.localVars!!)
    testOperandStack(frame.operandStack!!)
}

fun testOperandStack(operandStack: OperandStack) {
    operandStack.pushInt(100)
    operandStack.pushInt(-100)
    operandStack.pushLong(2997924580)
    operandStack.pushLong(-2997924580)
    operandStack.pushFloat(3.1415926f)
    operandStack.pushDouble(2.71828182845)
    operandStack.pushRef(null)
    println(operandStack.popRef())
    println(operandStack.popDouble())
    println(operandStack.popFloat())
    println(operandStack.popLong())
    println(operandStack.popLong())
    println(operandStack.popInt())
    println(operandStack.popInt())
}

fun testLocalVars(localVars: LocalVars) {
    localVars.setInt(0, 100)
    localVars.setInt(1, -100)
    localVars.setLong(2, 2997924580)
    localVars.setLong(4, -2997924580)
    localVars.setFloat(6, 3.141592f)
    localVars.setDouble(7, 2.71828182845)
    localVars.setRef(9, null)
    println(localVars.getInt(0))
    println(localVars.getInt(1))
    println(localVars.getLong(2))
    println(localVars.getLong(4))
    println(localVars.getFloat(6))
    println(localVars.getDouble(7))
    println(localVars.getRef(9))
}
