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

class IfICmpNe : BranchInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value1 != value2) {
            branch(frame, offset)
        }
    }
}

class IfICmpLt : BranchInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value1 < value2) {
            branch(frame, offset)
        }
    }
}

class IfICmpLe : BranchInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value1 <= value2) {
            branch(frame, offset)
        }
    }
}

class IfICmpGt : BranchInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value1 > value2) {
            branch(frame, offset)
        }
    }
}

class IfICmpGe : BranchInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val value2 = stack.popInt()
        val value1 = stack.popInt()
        if (value1 >= value2) {
            branch(frame, offset)
        }
    }
}
