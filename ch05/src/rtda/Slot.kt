package rtda

class Slot {
    fun copy(): Slot {
        return Slot().apply {
            this.num = this@Slot.num
            this.ref = this@Slot.ref
        }
    }

    var num: Int? = null
    var ref: Any? = null
}