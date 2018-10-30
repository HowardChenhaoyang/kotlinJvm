package instructions.stores

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.popRef
import rtda.setRef

class AStore : Index8Instruction() {
    override fun execute(frame: Frame) {
        aStore(frame, index)
    }
}

class AStore0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        aStore(frame, 0)
    }
}

class AStore1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        aStore(frame, 1)
    }
}

class AStore2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        aStore(frame, 2)
    }
}

class AStore3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        aStore(frame, 3)
    }
}

private fun aStore(frame: Frame, index: Int) {
    val ref = frame.operandStack!!.popRef()
    frame.localVars!!.setRef(index, ref)
}