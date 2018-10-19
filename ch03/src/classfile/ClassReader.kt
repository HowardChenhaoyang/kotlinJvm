package classfile



class ClassReader {

    lateinit var data: ByteArray

    fun readU1(): Short {
        val value = data[0]
        data = data.copyOfRange(1, data.size)
        return value.toShort()
    }

    fun readU2(): Int {
        val u2bytes = data.copyOfRange(0, 2)
        var num = 0
        for (byte in u2bytes) {
            num = num shl 8
            num = num or (byte.toInt() and 0xff)
        }
        data = data.copyOfRange(2, data.size)
        return num
    }

    fun readU4(): Long {
        val u4bytes = data.copyOfRange(0, 4)
        var num = 0L
        for (byte in u4bytes) {
            num = num shl 8
            num = num or (byte.toInt() and 0xff).toLong()
        }
        data = data.copyOfRange(4, data.size)
        return num
    }


    fun readU2s(): IntArray {
        val length = readU2()
        val nums = (0 until length).map {
            readU2()
        }
        return nums.toIntArray()
    }

    fun readBytes(n: Int): ByteArray {
        val bytes = data.copyOfRange(0, n)
        data = data.copyOfRange(1, data.size)
        return bytes
    }
}

