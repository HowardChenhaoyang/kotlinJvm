package instructions.comparisons

import instructions.base.BranchInstruction
import instructions.base.branch
import rtda.Frame

class IfICmpEq : BranchInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value1 == value2) {
            branch(frame, offset)
        }
    }
}