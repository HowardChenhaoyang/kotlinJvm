package instructions.control

import instructions.base.BranchInstruction
import instructions.base.branch
import rtda.Frame

class Goto : BranchInstruction() {
    override fun execute(frame: Frame) {
        branch(frame, offset)
    }
}