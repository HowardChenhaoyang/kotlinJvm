package instructions.math

import instructions.base.NoOperandsInstruction
import rtda.Frame

class IShl : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt() and 0x1f
        val value1 = stack.popInt()
        val result = value1 shl value2
        stack.pushInt(result)
    }
}
class IShR : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt() and 0x1f
        val value1 = stack.popInt()
        val result = value1 shr value2
        stack.pushInt(result)
    }
}
class IUShr : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt() and 0x1f
        val value1 = stack.popInt()
        val result = value1 ushr value2
        stack.pushInt(result)
    }
}
class LShl : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt() and 0x3f
        val value1 = stack.popLong()
        val result = value1 shl value2
        stack.pushLong(result)
    }
}
class LShr : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt() and 0x3f
        val value1 = stack.popLong()
        val result = value1 shr value2
        stack.pushLong(result)
    }
}
class LUShr : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt() and 0x3f
        val value1 = stack.popLong()
        val result = value1 ushr value2
        stack.pushLong(result)
    }
}