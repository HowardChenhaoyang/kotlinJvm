package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DAdd : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val num1 = stack.popDouble()
        val num2 = stack.popDouble()
        stack.pushDouble(num1 + num2)
    }
}

class FAdd : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val num1 = stack.popFloat()
        val num2 = stack.popFloat()
        stack.pushFloat(num1 + num2)
    }
}

class IAdd : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val num1 = stack.popInt()
        val num2 = stack.popInt()
        stack.pushInt(num1 + num2)
    }
}

class LAdd : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val num1 = stack.popLong()
        val num2 = stack.popLong()
        stack.pushLong(num1 + num2)
    }
}
