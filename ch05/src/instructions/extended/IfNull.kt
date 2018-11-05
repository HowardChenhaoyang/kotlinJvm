package instructions.extended

import instructions.base.BranchInstruction
import instructions.base.branch
import rtda.Frame

class IfNull : BranchInstruction() {
    override fun execute(frame: Frame) {
        val ref = frame.operandStack!!.popRef()
        ref ?: branch(frame, offset)
    }
}

class IfNonNull : BranchInstruction() {
    override fun execute(frame: Frame) {
        val ref = frame.operandStack!!.popRef()
        if (ref != null) {
            branch(frame, offset)
        }
    }
}
