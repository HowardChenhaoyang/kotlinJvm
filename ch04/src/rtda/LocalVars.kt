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

fun LocalVars.getFloat(index: Int): Float = this[index].num!!.toByte().toFloat()

fun LocalVars.setLong(index: Int, value: Long) {
    this[index].num = value.toInt()
    this[index + 1].num = (value shr 32).toInt()
}

fun setDouble(index: Int,value:Double){
    val bits = value.toBits()
}