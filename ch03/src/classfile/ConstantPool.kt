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

}

fun ConstantPool.getNameAndType(index: Int): NameAndType {

}

fun ConstantPool.getClassName(index: Int): String {

}

fun ConstantPool.getUtf8(index: Int): String {

}