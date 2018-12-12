package rtda.heap

fun lookupMethodInClass(clazz: Class?, name: String, descriptor: String): Method? {
    clazz ?: return null
    var loopingClass: Class? = clazz
    while (loopingClass != null) {
        val method = loopingClass.methods?.find { it.name == name && it.descriptor == descriptor }
        if (method != null) {
            return method
        } else {
            loopingClass = loopingClass.superClass
        }
    }
    return null
}

fun lookupMethodInInterfaces(interfaces: Array<Class>?, name: String, descriptor: String): Method? {
    interfaces ?: return null
    for (iface in interfaces) {
        var method = iface.methods?.find { it.name == name && it.descriptor == descriptor }
        if (method != null) {
            return method
        }
        method = lookupMethodInInterfaces(iface.interfaces, name, descriptor)
        if (method != null) {
            return method
        }
    }
    return null
}