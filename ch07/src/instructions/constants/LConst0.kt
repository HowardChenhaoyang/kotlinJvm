package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame

class LConst0:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushLong(0L)
    }
}