package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushDouble

class DConst0:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
     frame.operandStack!!.pushDouble(0.0)
    }
}