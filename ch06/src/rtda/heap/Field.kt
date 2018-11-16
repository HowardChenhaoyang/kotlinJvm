package rtda.heap

import classfile.MemberInfo

class Field : ClassMember() {
    var constValueIndex: Int = -1
    var slotId: Int = -1
    fun copyAttributes(cfField: MemberInfo) {
        cfField.constantValueAttribute()?.let {
            constValueIndex = it.constantValueIndex
        }
    }

    fun isVolatile() = accessFlags and ACC_VOLATILE
    fun isTransient() = accessFlags and ACC_TRANSIENT
    fun isEnum() = accessFlags and ACC_ENUM
    fun isLongOrDouble() = descriptor == "J" || descriptor == "D"
    companion object {
        fun newFields(clazz: Class, cfFields: Array<MemberInfo>): Array<Field> {
            return cfFields.map {
                val field = Field()
                field.clazz = clazz
                field.copyMemberInfo(it)
                field.copyAttributes(it)
                return@map field
            }.toTypedArray()
        }
    }
}