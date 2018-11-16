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