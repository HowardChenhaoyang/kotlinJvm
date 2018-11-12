package instructions.base

abstract class NoOperandsInstruction:Instruction{
    override fun fetchOperands(reader: BytecodeReader) {
    }
}