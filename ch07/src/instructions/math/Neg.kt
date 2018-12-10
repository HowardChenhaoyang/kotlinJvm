package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DNeg:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushDouble(-stack.popDouble())
    }
}
class FNeg:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushFloat(-stack.popFloat())
    }
}
class INeg:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(-stack.popInt())
    }
}
class LNeg:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(-stack.popLong())
    }
}
