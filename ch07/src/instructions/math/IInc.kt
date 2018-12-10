package instructions.math

import instructions.base.BytecodeReader
import instructions.base.Instruction
import rtda.Frame
import rtda.getInt
import rtda.setInt

class IInc : Instruction {
    var index: Int = -1
    var constValue: Int = 0
    override fun fetchOperands(reader: BytecodeReader) {
        index = reader.readUint8()
        constValue = reader.readInt8()
    }

    override fun execute(frame: Frame) {
        val localVars = frame.localVars!!
        localVars.setInt(index, localVars.getInt(index) + constValue)
    }
}