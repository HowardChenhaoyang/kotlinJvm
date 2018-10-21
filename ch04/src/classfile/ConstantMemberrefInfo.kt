package classfile

interface ConstantMemberrefInfo {
    var constantPool: ConstantPool
    var classIndex: Int
    var nameAndTypeIndex: Int

    fun className(): String {
        return constantPool.getClassName(classIndex)
    }

    fun readInfo(classReader: ClassReader) {
        classIndex = classReader.readU2()
        nameAndTypeIndex = classReader.readU2()
    }

    fun nameAndDescriptor(): NameAndType {
        return constantPool.getNameAndType(nameAndTypeIndex)
    }
}