package rtda.heap

import classfile.ConstantMethodrefInfo

class MethodRef : MemberRef() {
    var method: Method? = null
    fun resolvedMethod(): Method {
        if (method == null) _resolvedMethod()
        return method!!
    }

    private fun _resolvedMethod() {
        // TODO
    }

    companion object {
        fun newMethodRef(constantPool: ConstantPool, constantMethodrefInfo: ConstantMethodrefInfo): MethodRef {
            val methodRef = MethodRef()
            methodRef.cp = constantPool
            methodRef.copyMemberRefInfo(constantMethodrefInfo)
            return methodRef
        }
    }
}