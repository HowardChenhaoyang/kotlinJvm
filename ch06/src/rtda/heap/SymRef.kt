package rtda.heap

open class SymRef {
    var cp: ConstantPool? = null
    var className = ""
    var clazz: Class? = null
    fun resolvedClass(): Class {
        if (clazz == null) {
            resolveClassRef()
        }
        return clazz!!
    }

    private fun resolveClassRef() {
        val d = cp!!.clazz
        val c = d.loader!!.loadClass(className)
        if (!c.isAccessibleTo(d)) {
            throw IllegalAccessError()
        }
        clazz = c
    }
}