package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DDiv : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val double1 = stack.popDouble()
        val double2 = stack.popDouble()
        stack.pushDouble(double2 / double1)
    }
}

class FDiv : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val float1 = stack.popFloat()
        val float2 = stack.popFloat()
        stack.pushFloat(float2 / float1)
    }
}

class IDiv : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val int1 = stack.popInt()
        val int2 = stack.popInt()
        if (int1 == 0) {
            throw ArithmeticException(" / by zero ")
        }
        stack.pushInt(int2 / int1)
    }
}

class LDiv : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val long1 = stack.popLong()
        val long2 = stack.popLong()
        if (long1 == 0L) {
            throw ArithmeticException(" / by zero ")
        }
        stack.pushLong(long2 / long1)
    }
}