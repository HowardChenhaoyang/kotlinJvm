package instructions.loads

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.getDouble
import rtda.pushDouble

class DLoad : Index8Instruction() {
    override fun execute(frame: Frame) {
        dLoad(frame, index)
    }
}

class DLoad0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dLoad(frame, 0)
    }
}

class DLoad1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dLoad(frame, 1)
    }
}

class DLoad2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dLoad(frame, 2)
    }
}

class DLoad3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dLoad(frame, 3)
    }
}

private fun dLoad(frame: Frame, index: Int) {
    val value = frame.localVars!!.getDouble(index)
    frame.operandStack!!.pushDouble(value)
}