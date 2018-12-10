package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame

class AConstNull:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushRef(null)
    }
}