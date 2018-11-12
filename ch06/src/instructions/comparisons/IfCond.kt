package instructions.comparisons

import instructions.base.BranchInstruction
import instructions.base.branch
import rtda.Frame

class IFeq : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack!!.popInt()
        if (value == 0) {
            branch(frame, offset)
        }
    }
}

class IFne : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack!!.popInt()
        if (value != 0) {
            branch(frame, offset)
        }
    }
}

class IFlt : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack!!.popInt()
        if (value < 0) {
            branch(frame, offset)
        }
    }
}

class IFle : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack!!.popInt()
        if (value <= 0) {
            branch(frame, offset)
        }
    }
}

class IFgt : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack!!.popInt()
        if (value > 0) {
            branch(frame, offset)
        }
    }
}

class IFge : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack!!.popInt()
        if (value >= 0) {
            branch(frame, offset)
        }
    }
}
