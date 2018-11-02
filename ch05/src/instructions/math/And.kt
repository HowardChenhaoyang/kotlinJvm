package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class IAnd:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popInt() and stack.popInt())
    }
}

class LAnd:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(stack.popLong() and stack.popLong())
    }
}