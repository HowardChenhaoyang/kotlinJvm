package rtda.heap

fun Class.isSubClassOf(other: Class): Boolean {
    var c = this.superClass
    while (c != null) {
        if (c == other) {
            return true
        }
        c = c.superClass
    }
    return false
}

fun Class.isSuperClassOf(other: Class): Boolean {
    return other.isSubClassOf(this)
}

fun Class.isAssignableFrom(other: Class): Boolean {
    if (this == other) return true
    return if (this.isInterface()) {
        other.isImplements(this)
    } else {
        other.isSubClassOf(this)
    }
}

/**
 * @param iface is Interface
 */
fun Class.isImplements(iface: Class): Boolean {
    var superClass: Class? = this
    do {
        if (superClass!!.interfaces?.find { it == iface || it.isSubInterfaceOf(iface) } != null) {
            return true
        }
        superClass = superClass.superClass
    } while (superClass != null)
    return false
}

/**
 * @param iface is Interface
 */
private fun Class.isSubInterfaceOf(iface: Class): Boolean {
    interfaces?.find { it == iface || it.isSubInterfaceOf(iface) } ?: return false
    return true
}