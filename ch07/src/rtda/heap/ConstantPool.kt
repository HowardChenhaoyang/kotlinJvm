package rtda.heap

import classfile.*

private typealias classFileConstantPool = classfile.ConstantPool

typealias Constant = Any


/**
 * 运行时常量池
 */
class ConstantPool {
    lateinit var clazz: Class
    var consts: Array<Constant>? = null

    fun getConstant(index: Int): Constant {
        return consts!!.getOrNull(index) ?: throw RuntimeException("No constants at index $index")
    }

    companion object {
        fun newConstantPool(clazz: Class, cfcp: classFileConstantPool): ConstantPool {
            val consts = Array(cfcp.size) { Constant() }
            val constantPool = ConstantPool().apply {
                this.clazz = clazz
                this.consts = consts
            }
            var index = 0
            while (index < consts.size) {
                val constantInfo = cfcp[index]
                when (constantInfo) {
                    is ConstantIntegerInfo -> {
                        consts[index] = constantInfo.value
                        index++
                    }
                    is ConstantFloatInfo -> {
                        consts[index] = constantInfo.value
                        index++
                    }
                    is ConstantLongInfo -> {
                        consts[index] = constantInfo.value
                        index++
                        index++
                    }
                    is ConstantDoubleInfo -> {
                        consts[index] = constantInfo.value
                        index++
                        index++
                    }
                    is ConstantStringInfo -> {
                        consts[index] = constantInfo.value
                        index++
                    }
                    is ConstantClassInfo -> {
                        consts[index] = ClassRef.newClassRef(constantPool, constantInfo)
                        index++
                    }
                    is ConstantFieldrefInfo -> {
                        consts[index] = FieldRef.newFieldRef(constantPool, constantInfo)
                        index++
                    }
                    is ConstantMethodrefInfo -> {
                        consts[index] = MethodRef.newMethodRef(constantPool, constantInfo)
                        index++
                    }
                    is ConstantInterfaceMethodrefInfo -> {
                        consts[index] = InterfaceMethodRef.newInterfaceMethodRef(constantPool, constantInfo)
                        index++
                    }
                    else -> index++
                }
            }
            return constantPool
        }
    }
}