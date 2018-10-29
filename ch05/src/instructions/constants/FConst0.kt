package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushFloat

class FConst0:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushFloat(0f)
    }
}