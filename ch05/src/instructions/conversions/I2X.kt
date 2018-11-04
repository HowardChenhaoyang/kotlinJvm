package instructions.conversions

import instructions.base.NoOperandsInstruction
import rtda.Frame

class I2B:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popInt().toByte().toInt())
    }
}
class I2C:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popInt().toChar().toInt())
    }
}
class I2S:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushInt(stack.popInt().toShort().toInt())
    }
}
class I2L:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushLong(stack.popInt().toLong())
    }
}
class I2F:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushFloat(stack.popInt().toFloat())
    }
}
class I2D:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        stack.pushDouble(stack.popInt().toDouble())
    }
}