package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame

class LConst1:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushLong(1L)
    }
}