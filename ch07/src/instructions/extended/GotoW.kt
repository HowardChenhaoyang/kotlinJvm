package instructions.extended

import instructions.base.BytecodeReader
import instructions.base.Instruction
import instructions.base.branch
import rtda.Frame

class GotoW : Instruction {
    private var offset = 0
    override fun fetchOperands(reader: BytecodeReader) {
        offset = reader.readInt32()
    }

    override fun execute(frame: Frame) {
        branch(frame, offset)
    }
}