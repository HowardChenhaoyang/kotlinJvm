package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DMul : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushDouble(stack.popDouble() * stack.popDouble())
    }
}

class FMul:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushFloat(stack.popFloat() * stack.popFloat())
    }
}

class IMul:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popInt() * stack.popInt())
    }
}

class LMul:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(stack.popLong() * stack.popLong())
    }
}



