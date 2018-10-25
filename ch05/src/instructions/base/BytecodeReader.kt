package instructions.base

class BytecodeReader {
    private var code: ByteArray? = null
    var pc: Int = -1
        private set

    fun reset(code: ByteArray, pc: Int) {
        this.code = code
        this.pc = pc
    }

    fun readUint8(): Int {
        val byte = code!![pc]
        pc++
        return byte.toInt() and 0xff
    }

    fun readInt8(): Int {
        val byte = code!![pc]
        pc++
        return byte.toInt()
    }

    fun readInt16(): Int {
        return readUInt16().toShort().toInt()
    }

    fun readUInt16(): Int {
        val byte1 = code!![pc]
        pc++
        val byte2 = code!![pc]
        pc++
        return ((byte1.toInt() and 0xff) shl 8) or (byte2.toInt() and 0xff)
    }

    fun readInt32(): Int {
        val byte1 = readUint8()
        val byte2 = readUint8()
        val byte3 = readUint8()
        val byte4 = readUint8()
        return (byte1 shl 24) or (byte2 shl 16) or (byte3 shl 8) or byte4
    }

    fun readInt32s(length: Int): IntArray {
        return (0 until length).map {
            readInt32()
        }.toIntArray()
    }

    fun skipPadding() {
        while (0 !== pc % 4) readUint8()
    }
}