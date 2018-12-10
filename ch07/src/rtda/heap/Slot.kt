package rtda.heap

typealias Slots = Array<Slot>

class Slot {
    var num: Int = 0
    var ref: Object? = null

    companion object {
        fun newSlots(slotCount: Int): Slots? {
            if (slotCount > 0) {
                return Array(slotCount) { Slot() }
            }
            return null
        }
    }
}

fun Slots.setInt(index: Int, value: Int) {
    this[index].num = value
}

fun Slots.getInt(index: Int): Int {
    return this[index].num
}

fun Slots.setFloat(index: Int, value: Float) {
    this[index].num = value.toBits()
}

fun Slots.getFloat(index: Int): Float {
    return Float.fromBits(this[index].num)
}

fun Slots.setLong(index: Int, value: Long) {
    this[index].num = value.toInt()
    this[index + 1].num = (value ushr 32).toInt()
}

fun Slots.getLong(index: Int): Long {
    val low = this[index].num
    val high = this[index + 1].num
    return (high shl 32).toLong() or low.toLong()
}

fun Slots.setDouble(index: Int, value: Double) {
    val bits = value.toBits()
    setLong(index, bits)
}

fun Slots.getDouble(index: Int): Double {
    return Double.fromBits(getLong(index))
}

fun Slots.setRef(index: Int, ref: Object?) {
    this[index].ref = ref
}

fun Slots.getRef(index: Int) = this[index].ref