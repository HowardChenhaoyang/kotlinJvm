package classfile

import java.io.UTFDataFormatException

sealed class ConstantInfo {
    abstract fun readInfo(classReader: ClassReader)

    companion object {
        fun readConstantInfo(classReader: ClassReader, constantPool: ConstantPool): ConstantInfo {
            val tag = classReader.readU1()
            val constantInfo = newConstantInfo(tag, constantPool)
            constantInfo.readInfo(classReader)
            return constantInfo
        }

        fun newConstantInfo(tag: Short, constantPool: ConstantPool): ConstantInfo =
                when (tag) {
                    ConstantInvokeDynamicInfo.tag -> ConstantInvokeDynamicInfo()
                    ConstantMethodHandleInfo.TAG -> ConstantMethodHandleInfo()
                    ConstantMethodTypeInfo.TAG -> ConstantMethodTypeInfo()
                    ConstantNameAndTypeInfo.TAG -> ConstantNameAndTypeInfo()
                    ConstantInterfaceMethodrefInfo.TAG -> ConstantInterfaceMethodrefInfo(constantPool)
                    ConstantMethodrefInfo.TAG -> ConstantMethodrefInfo(constantPool)
                    ConstantFieldrefInfo.TAG -> ConstantFieldrefInfo(constantPool)
                    ConstantClassInfo.TAG -> ConstantClassInfo(constantPool)
                    ConstantStringInfo.TAG -> ConstantStringInfo(constantPool)
                    ConstantUtf8Info.TAG -> ConstantUtf8Info()
                    ConstantDoubleInfo.TAG -> ConstantDoubleInfo()
                    ConstantFloatInfo.TAG -> ConstantFloatInfo()
                    ConstantIntegerInfo.TAG -> ConstantIntegerInfo()
                    ConstantLongInfo.TAG -> ConstantLongInfo()
                    else -> throw ClassFormatError("constant pool tag!")
                }

    }
}


class ConstantInvokeDynamicInfo : ConstantInfo() {
    override fun readInfo(classReader: ClassReader) {

    }

    companion object {
        val tag: Short
            get() = 18
    }
}

class ConstantMethodHandleInfo : ConstantInfo() {
    override fun readInfo(classReader: ClassReader) {

    }

    companion object {
        const val TAG: Short = 15
    }
}

class ConstantMethodTypeInfo : ConstantInfo() {
    override fun readInfo(classReader: ClassReader) {

    }

    companion object {
        const val TAG: Short = 16
    }
}

class ConstantNameAndTypeInfo : ConstantInfo() {
    var nameIndex: Int = -1
    var descriptorIndex: Int = -1

    override fun readInfo(classReader: ClassReader) {
        nameIndex = classReader.readU2()
        descriptorIndex = classReader.readU2()
    }

    companion object {
        const val TAG: Short = 12
    }
}

class ConstantInterfaceMethodrefInfo(override var constantPool: ConstantPool) : ConstantInfo(), ConstantMemberrefInfo {

    override var classIndex: Int = -1

    override var nameAndTypeIndex: Int = -1

    override fun readInfo(classReader: ClassReader) {
        super.readInfo(classReader)
    }

    companion object {
        const val TAG: Short = 11
    }
}

class ConstantMethodrefInfo(override var constantPool: ConstantPool) : ConstantInfo(), ConstantMemberrefInfo {
    override var classIndex: Int = -1

    override var nameAndTypeIndex: Int = -1


    override fun readInfo(classReader: ClassReader) {
        super.readInfo(classReader)
    }

    companion object {
        const val TAG: Short = 10
    }
}

class ConstantFieldrefInfo(override var constantPool: ConstantPool) : ConstantInfo(), ConstantMemberrefInfo {

    override var classIndex: Int = -1

    override var nameAndTypeIndex: Int = -1


    override fun readInfo(classReader: ClassReader) {
        super.readInfo(classReader)
    }

    companion object {
        const val TAG: Short = 9
    }
}

class ConstantClassInfo(private val constantPool: ConstantPool) : ConstantInfo() {
    var nameIndex: Int = -1
    val name: String
        get() = constantPool.getUtf8(nameIndex)

    override fun readInfo(classReader: ClassReader) {
        nameIndex = classReader.readU2()
    }

    companion object {
        const val TAG: Short = 7
    }
}

class ConstantStringInfo(private val constantPool: ConstantPool) : ConstantInfo() {
    var stringIndex: Int = -1

    val value: String
        get() = constantPool.getUtf8(stringIndex)

    override fun readInfo(classReader: ClassReader) {
        stringIndex = classReader.readU2()
    }

    companion object {
        const val TAG: Short = 8
    }
}

class ConstantUtf8Info : ConstantInfo() {
    lateinit var value: String
    override fun readInfo(classReader: ClassReader) {
        val length = classReader.readU2()
        val bytes = classReader.readBytes(length)
        value = readUtf8(bytes)
    }

    private fun readUtf8(bytearr: ByteArray): String {
        var c: Int
        var char2: Int
        var char3: Int
        var count = 0
        var chararr_count = 0
        val chararr = CharArray(bytearr.size)

        while (count < bytearr.size) {
            c = bytearr[count].toInt() and 0xff
            if (c > 127) break
            count++
            chararr[chararr_count++] = c.toChar()
        }

        while (count < bytearr.size) {
            c = bytearr[count].toInt() and 0xff
            when (c shr 4) {
                0, 1, 2, 3, 4, 5, 6, 7 -> {
                    /* 0xxxxxxx*/
                    count++
                    chararr[chararr_count++] = c.toChar()
                }
                12, 13 -> {
                    /* 110x xxxx   10xx xxxx*/
                    count += 2
                    if (count > bytearr.size)
                        throw UTFDataFormatException(
                                "malformed input: partial character at end")
                    char2 = bytearr[count - 1].toInt()
                    if (char2 and 0xC0 != 0x80)
                        throw UTFDataFormatException(
                                "malformed input around byte $count")
                    chararr[chararr_count++] = (c and 0x1F shl 6 or (char2 and 0x3F)).toChar()
                }
                14 -> {
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3
                    if (count > bytearr.size)
                        throw UTFDataFormatException(
                                "malformed input: partial character at end")
                    char2 = bytearr[count - 2].toInt()
                    char3 = bytearr[count - 1].toInt()
                    if (char2 and 0xC0 != 0x80 || char3 and 0xC0 != 0x80)
                        throw UTFDataFormatException(
                                "malformed input around byte " + (count - 1))
                    chararr[chararr_count++] = (c and 0x0F shl 12 or
                            (char2 and 0x3F shl 6) or
                            (char3 and 0x3F shl 0)).toChar()
                }
                else ->
                    /* 10xx xxxx,  1111 xxxx */
                    throw UTFDataFormatException(
                            "malformed input around byte $count")
            }
        }
        // The number of chars produced may be less than utflen
        return String(chararr, 0, chararr_count)
    }

    companion object {
        const val TAG: Short = 1
    }
}

class ConstantDoubleInfo : ConstantInfo() {
    var high: Long = 0
    var low: Long = 0
    override fun readInfo(classReader: ClassReader) {
        high = classReader.readU4()
        low = classReader.readU4()
    }

    companion object {
        const val TAG: Short = 6
    }
}

class ConstantFloatInfo : ConstantInfo() {
    var value: Long = 0
    override fun readInfo(classReader: ClassReader) {
        value = classReader.readU4()
    }

    companion object {
        const val TAG: Short = 4
    }
}

class ConstantIntegerInfo : ConstantInfo() {
    var value: Long = 0
    override fun readInfo(classReader: ClassReader) {
        value = classReader.readU4()
    }

    companion object {
        const val TAG: Short = 3
    }
}

class ConstantLongInfo : ConstantInfo() {
    var high: Long = 0
    var low: Long = 0
    override fun readInfo(classReader: ClassReader) {
        high = classReader.readU4()
        low = classReader.readU4()
    }

    companion object {
        const val TAG: Short = 5
    }
}