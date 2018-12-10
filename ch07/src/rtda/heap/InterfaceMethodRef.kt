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
            _resolveInterfaceMethodRef()
        }
        return method!!
    }

    private fun _resolveInterfaceMethodRef() {
        // TODO
    }
}