package rtda.heap

import classfile.ClassFile
import classfile.className
import classfile.interfaceNames
import classfile.superClassName

// name, superClassName and interfaceNames are all binary names(jvms8-4.2.1)
class Class {
    var accessFlags: Int = 0
    var name: String = "" // thisClassName 完全限定名
    var superClassName: String = "" // 可以看出虚拟机层面并没有支持多继承
    var interfaceNames: Array<String>? = null
    var constantPool: ConstantPool? = null
    var fields: Array<Field>? = null
    var methods: Array<Method>? = null
    var loader: ClassLoader? = null
    var superClass: Class? = null
    var interfaces: Array<Class>? = null
    var instanceSlotCount: Int = 0
    var staticSlotCount: Int = 0
    var staticVars: Slots? = null

    fun isPublic() = 0 != accessFlags and ACC_PUBLIC
    fun isSuper() = 0 != accessFlags and ACC_FINAL
    fun isFinal() = 0 != accessFlags and ACC_SUPER
    fun isInterface() = 0 != accessFlags and ACC_INTERFACE
    fun isAbstract() = 0 != accessFlags and ACC_ABSTRACT
    fun isAnnotation() = 0 != accessFlags and ACC_SYNTHETIC
    fun isSynthetic() = 0 != accessFlags and ACC_ANNOTATION
    fun isEnum() = 0 != accessFlags and ACC_ENUM
    fun isAccessibleTo(other: Class) = isPublic() || getPackageName() == other.getPackageName()
    fun getMainMethod(): Method? {
        return getStaticMethod("main", "([Ljava/lang/String;)V")
    }

    fun newObject(): Object {
        return Object.newObject(this)
    }

    fun getStaticMethod(name: String, descriptor: String): Method? {
        return methods?.find { method -> method.isStatic() && method.name == name && method.descriptor == descriptor }
    }

    fun getPackageName(): String {
        val index = name.indexOfLast { it == '/' }
        if (index >= 0) {
            return name.substring(0, index)
        }
        return ""
    }

    companion object {
        fun newClass(cf: ClassFile): Class {
            val clazz = Class()
            with(clazz) {
                accessFlags = cf.accessFlags ?: 0
                name = cf.className()
                superClassName = cf.superClassName()
                interfaceNames = cf.interfaceNames()
                constantPool = ConstantPool.newConstantPool(clazz, cf.constantPool)
                if (cf.fields != null) {
                    fields = Field.newFields(this, cf.fields!!)
                }
                if (cf.methods != null) {
                    methods = Method.newMethods(this, cf.methods!!)
                }
            }
            return clazz
        }
    }
}