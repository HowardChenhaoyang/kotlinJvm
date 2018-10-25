package instructions.base

import rtda.Frame

interface Instruction {
    fun fetchOperands(reader: BytecodeReader)
    fun execute(frame: Frame)
}