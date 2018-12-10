package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame

class IConst5:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushInt(5)
    }
}