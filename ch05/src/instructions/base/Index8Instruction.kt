package instructions.base

abstract class Index8Instruction:Instruction{
    var index:Int = -1
    override fun fetchOperands(reader: BytecodeReader) {
        index = reader.readUint8()
    }
}