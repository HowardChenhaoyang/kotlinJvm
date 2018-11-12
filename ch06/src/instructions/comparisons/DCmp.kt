package instructions.comparisons

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DCmpg : NoOperandsInstruction() {
    override fun execute(frame: Frame) {

    }
}

class DCmpl : NoOperandsInstruction() {
    override fun execute(frame: Frame) {

    }
}

private fun dcmp(frame: Frame, gFlag: Boolean) {
    val stack = frame.operandStack!!
    val value2 = stack.popDouble()
    val value1 = stack.popDouble()
    if (value1 == Double.NaN || value2 == Double.NaN) {
        stack.pushInt(if (gFlag) 1 else -1)
        return
    }
    when {
        value1 > value2 -> stack.pushInt(1)
        value1 == value2 -> stack.pushInt(0)
        value1 < value2 -> stack.pushInt(-1)
    }
}