package instructions.loads

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.getLong

class LLoad:Index8Instruction(){
    override fun execute(frame: Frame) {
        lLoad(frame, index)
    }
}


class LLoad0 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lLoad(frame, 0)
    }
}

class LLoad1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lLoad(frame, 1)
    }
}

class LLoad2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lLoad(frame, 2)
    }
}

class LLoad3 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        lLoad(frame, 3)
    }
}


private fun lLoad(frame: Frame, index:Int){
    val value = frame.localVars!!.getLong(index)
    frame.operandStack!!.pushLong(value)
}