package instructions.comparisons

import instructions.base.NoOperandsInstruction
import rtda.Frame

class FCmpg : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fCmp(frame, true)
    }
}

class FCmpl : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fCmp(frame, false)
    }
}


private fun fCmp(frame: Frame, gFlag: Boolean) {
    val stack = frame.operandStack!!
    val value2 = stack.popFloat()
    val value1 = stack.popFloat()
    if (value1 == Float.NaN || value2 == Float.NaN) {
        if (gFlag) {
            stack.pushInt(1)
        } else {
            stack.pushInt(-1)
        }
        return
    }
    when {
        value1 > value2 -> stack.pushInt(1)
        value1 == value2 -> stack.pushInt(0)
        value1 < value2 -> stack.pushInt(-1)
    }
}