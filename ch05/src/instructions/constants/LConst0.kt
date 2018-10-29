package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushLong

class LConst0:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushLong(0L)
    }
}