package classfile

class MemberInfo {
    var constantPool: ConstantPool? = null
    var accessFlags: Int? = null
    var nameIndex: Int? = null
    var descriptorIndex: Int? = null
    var attributes: Array<AttributeInfo>? = null

    fun codeAttribute(): CodeAttribute? {
        attributes ?: return null
        for (attribute in attributes!!) {
            when (attribute) {
                is CodeAttribute -> {
                    return attribute
                }
            }
        }
        return null
    }
}

val MemberInfo.name: String
    get() = constantPool!!.getUtf8(nameIndex!!)

val MemberInfo.descriptor: String
    get() = constantPool!!.getUtf8(descriptorIndex!!)