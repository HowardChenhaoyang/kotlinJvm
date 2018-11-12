package classfile

class ExceptionTableEntry {
    var startPc = 0
    var endPc = 0
    var handlerPc = 0
    var catchType = 0

    companion object {
        fun readExceptionTable(classReader: ClassReader): Array<ExceptionTableEntry> {
            val exceptionTableLength = classReader.readU2()
            return (0 until exceptionTableLength).map {
                ExceptionTableEntry().apply {
                    startPc = classReader.readU2()
                    endPc = classReader.readU2()
                    handlerPc = classReader.readU2()
                    catchType = classReader.readU2()
                }
            }.toTypedArray()
        }
    }
}