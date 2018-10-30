package rtda

class OperandStack {
    var size: Int = 0
    lateinit var slots: Array<Slot>

    fun pushInt(value: Int) {
        slots[size].num = value
        size++
    }

    fun popInt(): Int {
        size--
        return slots[size].num!!
    }

    fun pushFloat(value: Float) {
        slots[size].num = value.toBits()
        size++
    }


    fun popFloat(): Float {
        size--
        return Float.fromBits(slots[size].num!!)
    }

    fun pushLong(value: Long) {
        slots[size].num = value.toInt()
        slots[size + 1].num = (value shr 32).toInt()
        size += 2
    }

    fun popLong(): Long {
        size -= 2
        val low = slots[size].num!!.toLong() and 0xffffffff
        val high = slots[size + 1].num!!.toLong()
        return (high shl 32) or low
    }

    fun pushDouble(double: Double) {
        pushLong(double.toBits())
    }

    fun popDouble(): Double = Double.fromBits(popLong())


    fun pushRef(ref: Any?) {
        slots[size].ref = ref
        size++
    }

    fun popRef(): Any? {
        size--
        val ref = slots[size].ref
        slots[size].ref = null
        return ref
    }

    fun pushSlot(slot: Slot) {
        slots[size] = slot
        size++
    }

    fun popSlot():Slot {
        size--
        return slots[size]
    }


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