package instructions.stores

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.*

class DStore : Index8Instruction() {
    override fun execute(frame: Frame) {
        dStore(frame, index)
    }
}

class DStore0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dStore(frame, 0)
    }
}

class DStore1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dStore(frame, 1)
    }
}

class DStore2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dStore(frame, 2)
    }
}

class DStore3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        dStore(frame, 3)
    }
}

private fun dStore(frame: Frame, index: Int) {
    val value = frame.operandStack!!.popDouble()
    frame.localVars!!.setDouble(index, value)
}