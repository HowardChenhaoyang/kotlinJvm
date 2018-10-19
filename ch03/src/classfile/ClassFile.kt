package classfile

/*
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/
class ClassFile {
    var minorVersion: Int? = null
    var majorVersion: Int? = null
    lateinit var constantPool: ConstantPool
    var accessFlags: Int? = null
    var thisClass: Int? = null
    var superClass: Int? = null
    var interfaces: IntArray? = null
    var fields: Array<MemberInfo>? = null
    var methods: Array<MemberInfo>? = null
    var attributes: Array<AttributeInfo>? = null

    companion object {
        fun parse(data: ByteArray): ClassFile {
            val classReader = ClassReader().apply { this.data = data }
            val classFile = ClassFile()
            classFile.read(classReader)
            return classFile
        }
    }
}

fun ClassFile.read(classReader: ClassReader) {
    readAndCheckMagic(classReader)
    readAndCheckVersion(classReader)
    constantPool = readConstantPool(classReader)
    accessFlags = classReader.readU2()
    thisClass = classReader.readU2()
    superClass = classReader.readU2()
    interfaces = classReader.readU2s()
    fields = readMembers(classReader, constantPool)
    methods = readMembers(classReader, constantPool)
    attributes = AttributeInfo.readAttributes(classReader, constantPool)
}

private fun readAndCheckMagic(classReader: ClassReader) {
    val magic = classReader.readU4()
    if (magic != 0x00000000CAFEBABE) {
        throw ClassFormatError("magic!")
    }
}

private fun readAndCheckVersion(classReader: ClassReader) {
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
    throw UnsupportedClassVersionError()
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
    this.attributes = AttributeInfo.readAttributes(classReader, constantPool)
}

fun ClassFile.className(): String {
    return constantPool.getClassName(thisClass!!)
}

fun ClassFile.superClassName(): String {
    if (superClass != null && superClass!! > 0) {
        return constantPool.getClassName(superClass!!)
    }
    return ""
}

fun ClassFile.interfaceNames(): Array<String>? {
    return interfaces?.map { constantPool.getClassName(it) }?.toTypedArray()
}
