package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushInt

class IConst1:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushInt(1)
    }
}