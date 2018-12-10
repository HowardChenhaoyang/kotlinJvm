package rtda.heap

import classfile.ConstantInterfaceMethodrefInfo

class InterfaceMethodRef : MemberRef() {
    var method: Method? = null

    companion object {
        fun newInterfaceMethodRef(constantPool: ConstantPool, constantInterfaceMethodrefInfo: ConstantInterfaceMethodrefInfo): InterfaceMethodRef {
            val ref = InterfaceMethodRef()
            ref.cp = constantPool
            ref.copyMemberRefInfo(constantInterfaceMethodrefInfo)
            return ref
        }
    }

    fun resolvedInterfaceMethod(): Method {
        if (method == null) {
            resolveInterfaceMethodRefInternal()
        }
        return method!!
    }

    private fun resolveInterfaceMethodRefInternal() {
        val selfClass = cp!!.clazz
        val refrenceClass = resolvedClass()
        if (!refrenceClass.isInterface()) {
            throw IncompatibleClassChangeError()
        }
        val method = lookupInterfaceMethod(refrenceClass, name, descriptor) ?: throw NoSuchMethodError()
        if (!method.isAccessibleTo(selfClass)) {
            throw IllegalAccessError()
        }
        this.method = method
    }

    private fun lookupInterfaceMethod(iface: Class, name: String, descriptor: String) = iface.methods?.find { it.name == name && it.descriptor == descriptor }
            ?: lookupMethodInInterfaces(iface.interfaces, name, descriptor)
}