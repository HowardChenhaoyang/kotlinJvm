package instructions.comparisons

import instructions.base.BranchInstruction
import instructions.base.branch
import rtda.Frame

class IfACmpEq : BranchInstruction() {
    override fun execute(frame: Frame) {
        if (aCmp(frame)) {
            branch(frame, offset)
        }
    }
}

class IfACmpNe : BranchInstruction() {
    override fun execute(frame: Frame) {
        if (!aCmp(frame)) {
            branch(frame, offset)
        }
    }
}

private fun aCmp(frame: Frame): Boolean {
    val stack = frame.operandStack!!
    return stack.popRef() == stack.popRef()
}