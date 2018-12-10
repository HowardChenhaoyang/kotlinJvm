package instructions.stores

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.*

class FStore : Index8Instruction() {
    override fun execute(frame: Frame) {
        fStore(frame, index)
    }
}

class FStore0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fStore(frame, 0)
    }
}

class FStore1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fStore(frame, 1)
    }
}

class FStore2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fStore(frame, 2)
    }
}

class FStore3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        fStore(frame, 3)
    }
}

private fun fStore(frame: Frame, index: Int) {
    val value = frame.operandStack!!.popFloat()
    frame.localVars!!.setFloat(index, value)
}