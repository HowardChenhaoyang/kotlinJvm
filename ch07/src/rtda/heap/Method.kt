package rtda.heap

import classfile.MemberInfo
import instructions.base.MethodDescriptorParse

class Method : ClassMember() {
    var maxStack = 0
    var maxLocals = 0
    var code: ByteArray? = null
    var argSlotCount: Int = 0
    private fun copyAttributes(memberInfo: MemberInfo) {
        memberInfo.codeAttribute()?.let {
            maxStack = it.maxStack
            maxLocals = it.maxLocals
            code = it.code
        }
    }

    private fun calcArgSlotCount() {
        val parsedDescriptor = MethodDescriptorParse.parseMethodDescriptor(descriptor)
        parsedDescriptor.parameterTypes?.forEach {
            argSlotCount++
            if (it == "J" || it == "D") {
                argSlotCount++
            }
        }
        if (!isStatic()) {
            argSlotCount++
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
        fun newMethods(clazz: Class, cfMethods: Array<MemberInfo>): Array<Method> {
            return cfMethods.map {
                Method().apply {
                    this.clazz = clazz
                    this.copyMemberInfo(it)
                    this.copyAttributes(it)
                    this.calcArgSlotCount()
                }
            }.toTypedArray()
        }
    }
}