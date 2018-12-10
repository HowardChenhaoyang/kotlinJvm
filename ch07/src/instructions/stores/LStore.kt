package instructions.stores

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.*

class LStore : Index8Instruction() {
    override fun execute(frame: Frame) {
        lStore(frame, index)
    }
}

class LStore0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lStore(frame, 0)
    }
}

class LStore1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lStore(frame, 1)
    }
}

class LStore2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lStore(frame, 2)
    }
}

class LStore3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lStore(frame, 3)
    }
}

private fun lStore(frame: Frame, index: Int) {
    val value = frame.operandStack!!.popLong()
    frame.localVars!!.setLong(index, value)
}