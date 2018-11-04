package instructions.conversions

import instructions.base.NoOperandsInstruction
import rtda.Frame

class L2D : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushDouble(stack.popLong().toDouble())
    }
}

class L2F : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushFloat(stack.popLong().toFloat())
    }
}

class L2I : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popLong().toInt())
    }
}