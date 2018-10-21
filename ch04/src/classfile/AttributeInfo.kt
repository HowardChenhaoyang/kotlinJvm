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
                    SignatureAttribute.NAME -> SignatureAttribute(constantPool)
                    LocalVariableTypeTableAttribute.NAME -> LocalVariableTypeTableAttribute()
                    InnerClassesAttribute.NAME -> InnerClassesAttribute()
                    EnclosingMethodAttribute.NAME -> EnclosingMethodAttribute(constantPool)
                    BootstrapMethodsAttribute.NAME -> BootstrapMethodsAttribute()
                    else -> UnparsedAttribute(attrName, attrLen)
                }

    }
}

/*
Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
*/
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

/*
ConstantValue_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 constantvalue_index;
}
*/
// 只出现在field_info结构中
class ConstantValueAttribute : AttributeInfo() {
    var constantValueIndex: Int = -1
    override fun readInfo(classReader: ClassReader) {
        constantValueIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "ConstantValue"
    }
}


/*
Deprecated_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
}
*/
class DeprecatedAttribute : AttributeInfo() {

    override fun readInfo(classReader: ClassReader) {
    }

    companion object {
        const val NAME = "Deprecated"
    }
}

/*
Exceptions_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_exceptions;
    u2 exception_index_table[number_of_exceptions];
}
*/
// 方法抛出的异常属性
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


/*
LocalVariableTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 descriptor_index;
        u2 index;
    } local_variable_table[local_variable_table_length];
}
*/
class LocalVeriableTableAttribute : AttributeInfo() {
    var localVariableTable: Array<LocalVariableTableEntry>? = null
    override fun readInfo(classReader: ClassReader) {
        val localVariableTableLength = classReader.readU2()
        localVariableTable = (0 until localVariableTableLength).map {
            LocalVariableTableEntry().apply {
                startPc = classReader.readU2()
                length = classReader.readU2()
                nameIndex = classReader.readU2()
                descriptorIndex = classReader.readU2()
                index = classReader.readU2()
            }
        }.toTypedArray()
    }

    companion object {
        const val NAME = "LocalVariableTable"
    }
}


class SourceFileAttribute(private val constantPool: ConstantPool) : AttributeInfo() {
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


/*
Synthetic_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
}
*/
class SyntheticAttribute : AttributeInfo() {

    override fun readInfo(classReader: ClassReader) {
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


class SignatureAttribute(private val constantPool: ConstantPool) : AttributeInfo() {
    var signatureIndex: Int = -1
    val signature: String
        get() = constantPool.getUtf8(signatureIndex)

    override fun readInfo(classReader: ClassReader) {
        signatureIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "Signature"
    }
}

class LocalVariableTypeTableAttribute : AttributeInfo() {
    var localVariableTypeTable: Array<LocalVariableTypeTableEntry>? = null
    override fun readInfo(classReader: ClassReader) {
        val localVariableTypeTableLength = classReader.readU2()
        localVariableTypeTable = (0 until localVariableTypeTableLength).map {
            LocalVariableTypeTableEntry().apply {
                startPc = classReader.readU2()
                length = classReader.readU2()
                nameIndex = classReader.readU2()
                signatureIndex = classReader.readU2()
                index = classReader.readU2()
            }
        }.toTypedArray()
    }

    companion object {
        const val NAME = "LocalVariableTypeTable"
    }
}


/*
InnerClasses_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_classes;
    {   u2 inner_class_info_index;
        u2 outer_class_info_index;
        u2 inner_name_index;
        u2 inner_class_access_flags;
    } classes[number_of_classes];
}
*/
class InnerClassesAttribute : AttributeInfo() {
    override fun readInfo(classReader: ClassReader) {
        val numberOfClasses = classReader.readU2()
        (0 until numberOfClasses).map {
            InnerClassInfo().apply {
                innerClassInfoIndex = classReader.readU2()
                outerClassInfoIndex = classReader.readU2()
                innerNameIndex = classReader.readU2()
                innerClassAccessFlags = classReader.readU2()
            }
        }
    }

    companion object {
        const val NAME = "InnerClasses"
    }
}


/*
EnclosingMethod_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 class_index;
    u2 method_index;
}
*/
class EnclosingMethodAttribute(private val constantPool: ConstantPool) : AttributeInfo() {
    var classIndex: Int = -1
    var methodIndex: Int = -1

    val className: String
        get() = constantPool.getClassName(classIndex)

    val methodNameAndDescriptor: NameAndType
        get() = constantPool.getNameAndType(methodIndex)

    override fun readInfo(classReader: ClassReader) {
        classIndex = classReader.readU2()
        methodIndex = classReader.readU2()
    }

    companion object {
        const val NAME = "EnclosingMethod"
    }
}


class BootstrapMethodsAttribute : AttributeInfo() {
    var bootstrapMethods: Array<BootstrapMethod>? = null
    override fun readInfo(classReader: ClassReader) {
        val numBootstrapMethods = classReader.readU2()
        bootstrapMethods = (0 until numBootstrapMethods).map {
            BootstrapMethod().apply {
                bootstrapMethodRef = classReader.readU2()
                bootstrapArguments = classReader.readU2s()
            }
        }.toTypedArray()
    }

    companion object {
        const val NAME = "BootstrapMethods"
    }
}