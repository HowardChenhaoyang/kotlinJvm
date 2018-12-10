package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame

class Invokespecial : Index16Instruction() {
    override fun execute(frame: Frame) {
        frame.operandStack!!.popRef()
    }
}