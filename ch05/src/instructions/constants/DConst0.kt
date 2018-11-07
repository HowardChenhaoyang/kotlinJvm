package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame

class DConst0:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
     frame.operandStack!!.pushDouble(0.0)
    }
}