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


fun OperandStack.popFloat():Float{
    size--
    return Float.fromBits(slots[size].num!!)
}

fun OperandStack.pushLong(){

}