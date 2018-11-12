package rtda.heap

class Object {
    var clazz: Class? = null
    var fields: Slots? = null
    companion object {
        fun newObject(clazz: Class):Object{
            return Object().apply {
                this.clazz = clazz
                this.fields =
            }
        }
    }
}