package instructions.loads

import instructions.base.Index8Instruction
import instructions.base.NoOperandsInstruction
import rtda.Frame
import rtda.getRef

class ALoad : Index8Instruction() {
    override fun execute(frame: Frame) {
        aLoad(frame, index)
    }
}

class ALoad0:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        aLoad(frame, 0)
    }
}

class ALoad1:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        aLoad(frame, 1)
    }
}

class ALoad2:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        aLoad(frame, 2)
    }
}

class ALoad3:NoOperandsInstruction(){
    override fun execute(frame: Frame) {
        aLoad(frame, 3)
    }
}

private fun aLoad(frame: rtda.Frame, index: Int) {
    val ref = frame.localVars!!.getRef(index)
    frame.operandStack!!.pushRef(ref)
}