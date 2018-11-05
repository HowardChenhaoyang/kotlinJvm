package instructions.comparisons

import instructions.base.BranchInstruction
import instructions.base.branch
import rtda.Frame

class IfAcmp : BranchInstruction() {
    override fun execute(frame: Frame) {
        if (acmp(frame)) {
            branch(frame, offset)
        }
    }
}

class IfAcmpNe : BranchInstruction() {
    override fun execute(frame: Frame) {
        if (!acmp(frame)) {
            branch(frame, offset)
        }
    }
}

private fun acmp(frame: Frame): Boolean {
    val stack = frame.operandStack!!
    return stack.popRef() == stack.popRef()
}