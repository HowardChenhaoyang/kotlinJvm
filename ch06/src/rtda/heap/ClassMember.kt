package rtda.heap

import classfile.MemberInfo
import classfile.descriptor
import classfile.name

open class ClassMember {
    var accessFlags: Int = 0
    var name: String = ""
    var descriptor: String = ""
    lateinit var clazz: Class
    fun copyMemberInfo(memberInfo: MemberInfo) {
        accessFlags = memberInfo.accessFlags
        name = memberInfo.name
        descriptor = memberInfo.descriptor
    }

    fun isPublic() = 0 != accessFlags and ACC_PUBLIC
    fun isPrivate() = 0 != accessFlags and ACC_PRIVATE
    fun isProtected() = 0 != accessFlags and ACC_PROTECTED
    fun isStatic() = 0 != accessFlags and ACC_STATIC
    fun isFinal() = 0 != accessFlags and ACC_FINAL
    fun isSynthetic() = 0 != accessFlags and ACC_SYNTHETIC
    fun isAccessibleTo(d: Class): Boolean {
        if (d == clazz) {
            return true
        }
        if (isPublic()) {
            return true
        }
        if (isProtected()) {
            return d.isSubClassOf(clazz) || clazz.getPackageName() == d.getPackageName()
        }
        if (!isPrivate()) {
            return clazz.getPackageName() == d.getPackageName()
        }
        return false
    }
}