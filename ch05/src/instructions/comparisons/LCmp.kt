package instructions.comparisons

import instructions.base.NoOperandsInstruction
import rtda.Frame

class LCmp : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popLong()
        val value1 = stack.popLong()
        if (value1 > value2) {
            stack.pushInt(1)
        } else if (value1 == value2) {
            stack.pushInt(0)
        } else {
            stack.pushInt(-1)
        }
    }
}