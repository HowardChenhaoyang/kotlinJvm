package classfile

typealias ConstantPool = Array<ConstantInfo?>

fun readConstantPool(classReader: ClassReader): ConstantPool {
    val constantPoolCount = classReader.readU2()
    val constantPool = ConstantPool(constantPoolCount) { null }
    loop@ for (i in 1 until constantPoolCount) {
        when (constantPool[i - 1]) {
            is ConstantLongInfo, is ConstantDoubleInfo -> continue@loop
        }
        constantPool[i] = ConstantInfo.readConstantInfo(classReader, constantPool)
    }
    return constantPool
}

fun ConstantPool.getConstantInfo(index: Int): ConstantInfo {
    val constantInfo = this.getOrNull(index)
    if (constantInfo != null) {
        return constantInfo
    }
    throw RuntimeException("Invalid constant pool index: $index")
}

fun ConstantPool.getNameAndType(index: Int): NameAndType {
    val constantNameAndTypeInfo = getConstantInfo(index) as ConstantNameAndTypeInfo
    val name = getUtf8(constantNameAndTypeInfo.nameIndex)
    val type = getUtf8(constantNameAndTypeInfo.descriptorIndex)
    return NameAndType(name, type)
}

fun ConstantPool.getClassName(index: Int): String {
    val constantInfo = getConstantInfo(index) as ConstantClassInfo
    return getUtf8(constantInfo.nameIndex)
}

fun ConstantPool.getUtf8(index: Int): String {
    val constantInfo = getConstantInfo(index) as ConstantUtf8Info
    return constantInfo.value
}