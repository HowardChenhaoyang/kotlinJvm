package rtda

typealias LocalVars = Array<Slot>

class LocalVarsFactory {
    companion object {
        fun newLocalVars(maxLocals: Int): LocalVars? {
            if (maxLocals > 0) {
                return LocalVars(maxLocals) { Slot() }
            }
            return null
        }
    }
}

fun LocalVars.setInt(index: Int, value: Int) {
    this[index].num = value
}

fun LocalVars.getInt(index: Int) = this[index].num

fun LocalVars.setFloat(index: Int, value: Float) {
    this[index].num = value.toBits()
}

fun LocalVars.getFloat(index: Int): Float = Float.fromBits(this[index].num!!)

fun LocalVars.setLong(index: Int, value: Long) {
    this[index].num = value.toInt()
    this[index + 1].num = (value shr 32).toInt()
}

fun LocalVars.getLong(index: Int): Long {
    val low = this[index].num!!.toLong()
    val high = this[index + 1].num!!.toLong()
    return (high shl 32) or low
}

fun setDouble(index: Int, value: Double) {
    val bits = value.toBits()
}