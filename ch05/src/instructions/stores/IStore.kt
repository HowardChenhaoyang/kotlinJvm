package instructions.stores

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.*

class IStore : Index8Instruction() {
    override fun execute(frame: Frame) {
        iStore(frame, index)
    }
}

class IStore0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iStore(frame, 0)
    }
}

class IStore1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iStore(frame, 1)
    }
}

class IStore2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iStore(frame, 2)
    }
}

class IStore3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        iStore(frame, 3)
    }
}

private fun iStore(frame: Frame, index: Int) {
    val value = frame.operandStack!!.popInt()
    frame.localVars!!.setInt(index, value)
}