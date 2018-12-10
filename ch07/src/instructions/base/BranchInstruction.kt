package instructions.base

abstract class BranchInstruction:Instruction{
    var offset:Int = -1
    override fun fetchOperands(reader: BytecodeReader) {
        offset = reader.readInt16()
    }
}