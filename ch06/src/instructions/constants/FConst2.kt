package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame


class FConst2:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushFloat(2f)
    }
}