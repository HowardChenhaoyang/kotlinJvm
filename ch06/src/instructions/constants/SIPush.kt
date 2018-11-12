package instructions.constants

import instructions.base.BytecodeReader
import instructions.base.Instruction
import rtda.Frame

class SIPush:Instruction{
    private var value:Int = -1
    override fun fetchOperands(reader: BytecodeReader) {
        value = reader.readInt16()
    }

    override fun execute(frame: Frame) {
        frame.operandStack!!.pushInt(value)
    }

}