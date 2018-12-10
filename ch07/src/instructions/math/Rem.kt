package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DRem : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popDouble()
        val value1 = stack.popDouble()
        stack.pushDouble(value1 % value2)
    }
}
class FRem : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popFloat()
        val value1 = stack.popFloat()
        stack.pushFloat(value1 % value2)
    }
}
class IRem : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value2 == 0){
            throw ArithmeticException(" / by zero")
        }
        stack.pushInt(value1 % value2)
    }
}
class LRem : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popLong()
        val value1 = stack.popLong()
        if (value2 == 0L){
            throw ArithmeticException(" / by zero")
        }
        stack.pushLong(value1 % value2)
    }
}
