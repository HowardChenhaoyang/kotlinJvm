package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DSub : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushDouble(-stack.popDouble() + stack.popDouble())
    }
}

class FSub : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushFloat(-stack.popFloat() + stack.popFloat())
    }
}

class ISub : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(-stack.popInt() + stack.popInt())
    }
}

class LSub : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(-stack.popLong() + stack.popLong())
    }
}