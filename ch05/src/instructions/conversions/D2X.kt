package instructions.conversions

import instructions.base.NoOperandsInstruction
import rtda.Frame

class D2F : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushFloat(stack.popDouble().toFloat())
    }
}

class D2I : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popDouble().toInt())
    }
}

class D2L : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(stack.popDouble().toLong())
    }
}

