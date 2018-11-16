package rtda.heap

import classfile.ClassFile
import classpath.ClassPath
import classpath.EntryReadClassResult
import kotlin.test.todo

class ClassLoader {
    var classPath: ClassPath? = null
    var classMap: MutableMap<String, Class>? = null
    fun loadClass(name: String): Class {
        return classMap?.get(name) ?: loadNonArrayClass(name)
    }

    private fun loadNonArrayClass(className: String):Class{
        return readClass(className).bytes.run {
            defineClass(this)
        }.apply {
            link(this)
        }
    }

    private fun readClass(className: String): EntryReadClassResult {
        try {
            return classPath!!.readClass(className)
        } catch (e: Exception) {
            e.printStackTrace()
            throw ClassNotFoundException(className)
        }
    }


    private fun defineClass(data: ByteArray): Class {
        val clazz = parseClass(data)
        clazz.loader = this
        resolveSuperClass(clazz)
        resolveInterfaces(clazz)
        classMap!![clazz.name] = clazz
        return clazz
    }

    private fun parseClass(data: ByteArray): Class {
        return Class.newClass(ClassFile.parse(data))
    }

    private fun resolveSuperClass(clazz: Class) {
        if ("java/lang/Object" != clazz.name) {
            clazz.superClass = clazz.loader!!.loadClass(clazz.superClassName)
        }
    }

    private fun resolveInterfaces(clazz: Class) {
        val interfaceCount = clazz.interfaceNames?.size ?: 0
        if (interfaceCount > 0) {
            clazz.interfaces = clazz.interfaceNames?.map { interfaceName ->
                clazz.loader!!.loadClass(interfaceName)
            }?.toTypedArray()
        }
    }

    private fun link(clazz: Class) {
        verify(clazz)
        prePare(clazz)
    }

    private fun verify(clazz: Class) {
        todo { }
    }

    private fun prePare(clazz: Class) {
        calcInstanceFieldSlotIds(clazz)
        calcStaticFieldSlotIds(clazz)
        allocAndInitStaticVars(clazz)
    }

    private fun calcInstanceFieldSlotIds(clazz: Class) {
        var slotId = 0
        if (clazz.superClass != null) {
            slotId = clazz.superClass!!.instanceSlotCount
        }
        clazz.fields?.forEach { field ->
            if (!field.isStatic()) {
                field.slotId = slotId
                slotId++
                if (field.isLongOrDouble()) {
                    slotId++
                }
            }
        }
    }

    private fun calcStaticFieldSlotIds(clazz: Class) {
        var slotId = 0
        clazz.fields?.forEach {
            if (it.isStatic()) {
                it.slotId = slotId
                slotId++
                if (it.isLongOrDouble()) {
                    slotId++
                }
            }
        }
        clazz.staticSlotCount = slotId
    }

    private fun allocAndInitStaticVars(clazz: Class) {
        clazz.staticVars = Slot.newSlots(clazz.staticSlotCount)
        clazz.fields?.forEach {
            if (it.isStatic() && it.isFinal()) {
                initStaticFinalVar(clazz, it)
            }
        }
    }

    /**
     * z-boolean
     * b-byte
     * c-char
     * s-short
     * i-int
     * j-long
     * f-float
     * d-double
     * v-void
     */
    private fun initStaticFinalVar(clazz: Class, field: Field) {
        val staticVars = clazz.staticVars
        val constantPool = clazz.constantPool
        val constValueIndex = field.constValueIndex
        val slotId = field.slotId
        if (constValueIndex > 0) {
            when (field.descriptor) {
                "Z", "B", "C", "S", "I" -> {
                    val value = constantPool!!.getConstant(constValueIndex).value as Int
                    staticVars!!.setInt(slotId, value)
                }
                "J" -> {
                    val value = constantPool!!.getConstant(constValueIndex).value as Long
                    staticVars!!.setLong(slotId, value)
                }
                "F" -> {
                    val value = constantPool!!.getConstant(constValueIndex).value as Float
                    staticVars!!.setFloat(slotId, value)
                }
                "D" -> {
                    val value = constantPool!!.getConstant(constValueIndex).value as Double
                    staticVars!!.setDouble(slotId, value)
                }
                "Ljava/lang/String;" -> {
                    TODO()
                }
            }
        }
    }

    companion object {
        fun newClassLoader(classPath: ClassPath):ClassLoader{
            return ClassLoader().apply {
                this.classPath = classPath
                this.classMap = mutableMapOf()
            }
        }
    }
}