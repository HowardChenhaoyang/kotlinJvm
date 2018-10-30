package instructions.stack

import instructions.base.NoOperandsInstruction
import rtda.Frame

class Dup : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val slot = frame.operandStack!!.popSlot()
        frame.operandStack!!.pushSlot(slot)
        frame.operandStack!!.pushSlot(slot.copy())
    }
}