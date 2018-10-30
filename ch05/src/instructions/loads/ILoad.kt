package instructions.loads

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.getInt

class ILoad : Index8Instruction() {
    override fun execute(frame: Frame) {
        iLoad(frame, index)
    }
}

class ILoad0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iLoad(frame, 0)
    }
}

class ILoad1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iLoad(frame, 1)
    }
}

class ILoad2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iLoad(frame, 2)
    }
}

class ILoad3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iLoad(frame, 3)
    }
}

private fun iLoad(frame: Frame, index: Int) {
    val value = frame.localVars!!.getInt(index)
    frame.operandStack!!.pushInt(value)
}