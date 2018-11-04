package instructions.conversions

import instructions.base.NoOperandsInstruction
import rtda.Frame

class F2D:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushDouble(stack.popFloat().toDouble())
    }
}
class F2I:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popFloat().toInt())
    }
}
class F2L:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(stack.popFloat().toLong())
    }
}

