package instructions.loads

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.getFloat


class FLoad : Index8Instruction() {
    override fun execute(frame: Frame) {
        fload(frame, index)
    }
}

class FLoad0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fload(frame, 0)
    }
}

class FLoad1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fload(frame, 1)
    }
}

class FLoad2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fload(frame, 2)
    }
}

class FLoad3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fload(frame, 3)
    }
}

private fun fload(frame: Frame, index: Int) {
    val value = frame.localVars!!.getFloat(index)
    frame.operandStack!!.pushFloat(value)
}