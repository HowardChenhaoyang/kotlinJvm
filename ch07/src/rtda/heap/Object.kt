package rtda.heap

class Object {
    var clazz: Class? = null
    var fields: Slots? = null

    fun isInstanceOf(clazz: Class) = clazz.isAssignableFrom(this.clazz!!)

    companion object {
        fun newObject(clazz: Class): Object {
            return Object().apply {
                this.clazz = clazz
                this.fields = Slot.newSlots(clazz.instanceSlotCount)
            }
        }
    }
}