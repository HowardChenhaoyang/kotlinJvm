package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.pushRef

class AConstNull:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        frame.operandStack!!.pushRef(null)
    }
}