package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class IOr:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        stack.pushInt(value1 or value2)
    }
}
class LOr:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popLong()
        val value1 = stack.popLong()
        stack.pushLong(value1 or value2)
    }
}

