package rtda.heap

import classfile.ConstantMethodrefInfo

class MethodRef : MemberRef() {
    var method: Method? = null

    fun resolvedMethod(): Method {
        if (method == null) resolvedMethodInternal()
        return method!!
    }

    private fun resolvedMethodInternal() {
        val selfClazz = cp!!.clazz
        val refrenceClass = resolvedClass()
        if (refrenceClass.isInterface()) {
            throw IncompatibleClassChangeError()
        }
        val method = lookupMethod(selfClazz, name, descriptor) ?: throw NoSuchMethodError()
        if (!method.isAccessibleTo(selfClazz)) {
            throw IllegalAccessError()
        }
        this.method = method
    }

    private fun lookupMethod(clazz: Class, name: String, descriptor: String): Method? =
            lookupMethodInClass(clazz, name, descriptor)
                    ?: lookupMethodInInterfaces(clazz.interfaces, name, descriptor)

    companion object {
        fun newMethodRef(constantPool: ConstantPool, constantMethodrefInfo: ConstantMethodrefInfo): MethodRef {
            val methodRef = MethodRef()
            methodRef.cp = constantPool
            methodRef.copyMemberRefInfo(constantMethodrefInfo)
            return methodRef
        }
    }
}