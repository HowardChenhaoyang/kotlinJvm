package instructions.stack

import instructions.base.NoOperandsInstruction
import rtda.Frame

class Pop:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.popSlot()
    }
}

class Pop2:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.popSlot()
        frame.operandStack!!.popSlot()
    }
}