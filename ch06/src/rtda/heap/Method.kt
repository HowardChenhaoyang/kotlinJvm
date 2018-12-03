package rtda.heap

import classfile.MemberInfo

class Method : ClassMember() {
    var maxStack = 0
    var maxLocals = 0
    var code: ByteArray? = null

    private fun copyAttributes(memberInfo: MemberInfo) {
        memberInfo.codeAttribute()?.let {
            maxStack = it.maxStack
            maxLocals = it.maxLocals
            code = it.code
        }
    }


    operator fun component1() = maxStack
    operator fun component2() = maxLocals

    fun isSynchronized() = 0 != accessFlags and ACC_SYNCHRONIZED
    fun isBridge() = 0 != accessFlags and ACC_BRIDGE
    fun isVarargs() = 0 != accessFlags and ACC_VARARGS
    fun isNative() = 0 != accessFlags and ACC_NATIVE
    fun isAbstract() = 0 != accessFlags and ACC_ABSTRACT
    fun isStrict() = 0 != accessFlags and ACC_STRICT

    companion object {
        fun newMethod(clazz: Class, cfMethods: Array<MemberInfo>): Array<Method> {
            return cfMethods.map {
                Method().apply {
                    this.clazz = clazz
                    this.copyMemberInfo(it)
                    this.copyAttributes(it)
                }
            }.toTypedArray()
        }
    }
}