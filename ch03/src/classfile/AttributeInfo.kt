package classfile


sealed class AttributeInfo {
    abstract fun readInfo(classReader: ClassReader)

    companion object {
        fun readAttributes(classReader: ClassReader, constantPool: ConstantPool): Array<AttributeInfo> {
            val attributesCount = classReader.readU2()
            return (0 until attributesCount).map {
                readAttribute(classReader, constantPool)
            }.toTypedArray()
        }

        private fun readAttribute(classReader: ClassReader, constantPool: ConstantPool): AttributeInfo {
            val attrNameIndex = classReader.readU2()
            val attrName = constantPool.getUtf8(attrNameIndex)
            val attrLen = classReader.readU4()
            val attrInfo = newAttributeInfo(attrName, attrLen, constantPool)
            attrInfo.readInfo(classReader)
            return attrInfo
        }

        private fun newAttributeInfo(attrName: String, attrLen: Long, constantPool: ConstantPool): AttributeInfo =
                when (attrName) {
                    CodeAttribute.NAME -> CodeAttribute(constantPool)
                    ConstantValueAttribute.NAME -> ConstantValueAttribute()
                    DeprecatedAttribute.NAME -> DeprecatedAttribute()
                    ExceptionsAttribute.NAME -> ExceptionsAttribute()
                    LineNumberTableAttribute.NAME -> LineNumberTableAttribute()
                    LocalVeriableTableAttribute.NAME -> LocalVeriableTableAttribute()
                    SourceFileAttribute.NAME -> SourceFileAttribute(constantPool)
                    SyntheticAttribute.NAME -> SyntheticAttribute()
                    else -> UnparsedAttribute(attrName, attrLen)
                }

    }
}

class CodeAttribute(private val constantPool: ConstantPool) : AttributeInfo() {
    var maxStack: Int = 0
    var maxLocals: Int = 0
    var code: ByteArray? = null
    var exceptionTable: Array<ExceptionTableEntry>? = null
    var attributes: Array<AttributeInfo>? = null

    override fun readInfo(classReader: ClassReader) {
        maxStack = classReader.readU2()
        maxLocals = classReader.readU2()
        val codeLength = classReader.readU4()
        code = classReader.readBytes(codeLength.toInt())
        exceptionTable = ExceptionTableEntry.readExceptionTable(classReader)
        attributes = readAttributes(classReader, constantPool)
    }

    companion object {
        const val NAME = "Code"
    }
}

class ConstantValueAttribute : AttributeInfo() {
    var constantValueIndex: Int = -1
    override fun readInfo(classReader: ClassReader) {
        constantValueIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "ConstantValue"
    }
}

class DeprecatedAttribute : AttributeInfo() {
    var nameIndex: Int = -1

    override fun readInfo(classReader: ClassReader) {
        nameIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "Deprecated"
    }
}

class ExceptionsAttribute : AttributeInfo() {
    var exceptionIndexTable: IntArray? = null
    override fun readInfo(classReader: ClassReader) {
        exceptionIndexTable = classReader.readU2s()
    }

    companion object {
        const val NAME = "Exceptions"
    }
}

class LineNumberTableAttribute : AttributeInfo() {
    var lineNumberTable: Array<LineNumberTableEntry>? = null
    override fun readInfo(classReader: ClassReader) {
        val lineNumberTableLength = classReader.readU2()
        lineNumberTable = (0 until lineNumberTableLength).map {
            LineNumberTableEntry().apply {
                startPc = classReader.readU2()
                lineNumber = classReader.readU2()
            }
        }.toTypedArray()
    }

    companion object {
        const val NAME = "LineNumberTable"
    }
}

class LocalVeriableTableAttribute : AttributeInfo() {
    override fun readInfo(classReader: ClassReader) {

    }

    companion object {
        const val NAME = "LocalVariableTable"
    }
}


class SourceFileAttribute(private val constantPool: ConstantPool) : AttributeInfo() {
    var nameIndex: Int = -1
    var attrLength: Long = -1 // 该值必须为2
    var sourceFileIndex: Int = -1
    val fileName: String
        get() = constantPool.getUtf8(sourceFileIndex)

    override fun readInfo(classReader: ClassReader) {
        sourceFileIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "SourceFile"
    }
}


class SyntheticAttribute : AttributeInfo() {
    var nameIndex: Int = -1

    override fun readInfo(classReader: ClassReader) {
        nameIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "Synthetic"
    }
}

class UnparsedAttribute(private val name: String, private val length: Long) : AttributeInfo() {
    var info: ByteArray? = null
    override fun readInfo(classReader: ClassReader) {
        info = classReader.readBytes(length.toInt())
    }
}