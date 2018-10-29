package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushLong

class LConst1:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushLong(1L)
    }
}