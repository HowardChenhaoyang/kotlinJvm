package rtda

import rtda.heap.Object

class Slot {
    fun copy(): Slot {
        return Slot().apply {
            this.num = this@Slot.num
            this.ref = this@Slot.ref
        }
    }

    var num: Int? = null
    var ref: Object? = null
}