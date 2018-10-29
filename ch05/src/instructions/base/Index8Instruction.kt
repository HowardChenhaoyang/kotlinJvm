package instructions.base

abstract class Index8Instruction:Instruction{
    protected var index:Int = -1
    override fun fetchOperands(reader: BytecodeReader) {
        index = reader.readUint8()
    }
}