package classfile

class ClassFile {
    var minorVersion: Int? = null
    var majorVersion: Int? = null
    var constantPool: ConstantPool? = null
    var accessFlags: Int? = null
    var thisClass: Int? = null
    var superClass: Int? = null
    var interfaces: Array<Int>? = null
    var fields: Array<MemberInfo>? = null
    var methods: Array<MemberInfo>? = null
    var attributes: Array<AttributeInfo>? = null
}

fun parse(data: Array<Byte>): ClassFile {
    val classReader = ClassReader().apply { this.data = data }
    val classFile = ClassFile()
    classFile.read(classReader)
    return classFile
}

fun ClassFile.read(classReader: ClassReader) {
    readAndCheckMagic(classReader)
    readAndCheckVersion(classReader)
    constantPool = readConstantPool(classReader)
    accessFlags = classReader.readU2()
    thisClass = classReader.readU2()
    superClass = classReader.readU2()
    interfaces = classReader.readU2s()
    fields = readMembers(classReader, constantPool!!)
    methods = readMembers(classReader, constantPool!!)
    attributes = readAttributes(classReader, constantPool!!)
}

fun ClassFile.readAndCheckMagic(classReader: ClassReader) {
    val magic = classReader.readU4()
    assert(magic == 0x00000000CAFEBABE) {
        "java.lang.ClassFormatError: magic!"
    }
}

fun ClassFile.readAndCheckVersion(classReader: ClassReader) {
    val minorVersion = classReader.readU2()
    val majorVersion = classReader.readU2()
    when (majorVersion) {
        45 -> return
        in 46..52 -> {
            if (minorVersion == 0) {
                return
            }
        }
    }
    assert(false) {
        "java.lang.UnsupportedClassVersionError!"
    }
}


fun readMembers(classReader: ClassReader, constantPool: ConstantPool): Array<MemberInfo>? {
    val memberCount = classReader.readU2()
    if (memberCount == 0) {
        return null
    }
    return (0..memberCount).map {
        readMember(classReader, constantPool)
    }.toTypedArray()
}

fun readMember(classReader: ClassReader, constantPool: ConstantPool): MemberInfo = MemberInfo().apply {
    this.constantPool = constantPool
    this.accessFlags = classReader.readU2()
    this.nameIndex = classReader.readU2()
    this.descriptorIndex = classReader.readU2()
    this.attributes = readAttributes(classReader, constantPool)
}

fun readAttributes(classReader: ClassReader, constantPool: ConstantPool): Array<AttributeInfo>? {

}

fun ClassFile.className(): String {
    return constantPool!!.getClassName(thisClass!!)
}

fun ClassFile.superClassName(): String {
    if (superClass != null && superClass!! > 0) {
        return constantPool!!.getClassName(superClass!!)
    }
    return ""
}

fun ClassFile.interfaceNames(): Array<String>? {
    return interfaces?.map { constantPool!!.getClassName(it) }?.toTypedArray()
}
