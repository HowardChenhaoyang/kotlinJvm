package rtda

class OperandStack {
    var size: Int = 0
    lateinit var slots: Array<Slot>

    companion object {
        fun newOperandStack(maxStack: Int): OperandStack? {
            if (maxStack > 0) {
                return OperandStack().apply {
                    slots = Array(maxStack) { Slot() }
                }
            }
            return null
        }
    }
}

fun OperandStack.pushInt(value: Int) {
    slots[size].num = value
    size++
}

fun OperandStack.popInt(): Int {
    size--
    return slots[size].num!!
}

fun OperandStack.pushFloat(value: Float) {
    slots[size].num = value.toBits()
    size++
}


fun OperandStack.popFloat(): Float {
    size--
    return Float.fromBits(slots[size].num!!)
}

fun OperandStack.pushLong(value: Long) {
    slots[size].num = value.toInt()
    slots[size + 1].num = (value shr 32).toInt()
    size += 2
}

fun OperandStack.popLong(): Long {
    size -= 2
    val low = slots[size].num!!.toLong() and 0xffffffff
    val high = slots[size + 1].num!!.toLong()
    return (high shl 32) or low
}

fun OperandStack.pushDouble(double: Double) {
    pushLong(double.toBits())
}

fun OperandStack.popDouble(): Double = Double.fromBits(popLong())


fun OperandStack.pushRef(ref: Any?) {
    slots[size].ref = ref
    size++
}

fun OperandStack.popRef(): Any? {
    size--
    val ref = slots[size].ref
    slots[size].ref = null
    return ref
}