package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushDouble

class DConst1:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushDouble(1.0)
    }
}