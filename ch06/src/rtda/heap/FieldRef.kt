package rtda.heap

import classfile.ConstantFieldrefInfo

class FieldRef : MemberRef() {
    var field: Field? = null

    companion object {
        fun newFieldRef(constantPool: ConstantPool, refInfo: ConstantFieldrefInfo): FieldRef {
            val fieldRef = FieldRef()
            fieldRef.cp = constantPool
            fieldRef.copyMemberRefInfo(refInfo)
            return fieldRef
        }
    }

    fun resolvedField(): Field {
        if (field == null) {
            _resolveFieldRef()
        }
        return field!!
    }

    private fun _resolveFieldRef() {
        val clazz = cp!!.clazz
        val resolvedClass = resolvedClass()
        val field = lookupField(clazz, name, descriptor) ?: throw NoSuchFieldError()
        if (!field.isAccessibleTo(resolvedClass)){
            throw IllegalAccessError()
        }
        this.field = field
    }

    private fun lookupField(clazz: Class, name: String, descriptor: String): Field? {
        var field: Field? = clazz.fields?.find { it.name == name && it.descriptor == descriptor }
        if (field != null) {
            return field
        }
        clazz.interfaces?.forEach {
            field = lookupField(it, name, descriptor)
            if (field != null) {
                return field
            }
        }
        if (clazz.superClass != null) {
            return lookupField(clazz.superClass!!, name, descriptor)
        }
        return null
    }
}