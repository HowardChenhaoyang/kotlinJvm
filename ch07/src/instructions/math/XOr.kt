package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class IXor : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popInt() xor stack.popInt())
    }
}

class LXor : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(stack.popLong() xor stack.popLong())
    }
}
