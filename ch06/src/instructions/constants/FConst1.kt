package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame


class FConst1:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushFloat(1f)
    }
}