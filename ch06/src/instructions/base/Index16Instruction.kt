package instructions.base

abstract class Index16Instruction : Instruction {
    var index: Int = -1
    override fun fetchOperands(reader: BytecodeReader) {
        index = reader.readUInt16()
    }
}