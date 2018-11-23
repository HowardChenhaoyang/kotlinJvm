package rtda.heap

import classfile.*

private typealias classFileConstantPool = classfile.ConstantPool

class Constant {
    var value: Any? = null
}

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
            for ((index, const) in consts.withIndex()) {
                mapToConstant(cfcp[index], constantPool, const)
            }
            return constantPool
        }

        private fun mapToConstant(constantInfo: ConstantInfo?, constantPool: ConstantPool, constant: Constant) {
            when (constantInfo) {
                is ConstantIntegerInfo -> {
                    constant.value = constantInfo.value
                }
                is ConstantFloatInfo -> {
                    constant.value = constantInfo.value
                }
                is ConstantLongInfo -> {
                    constant.value = constantInfo.value
                }
                is ConstantDoubleInfo -> {
                    constant.value = constantInfo.value
                }
                is ConstantStringInfo -> {
                    constant.value = constantInfo.value
                }
                is ConstantClassInfo -> {
                    constant.value = ClassRef.newClassRef(constantPool, constantInfo)
                }
                is ConstantFieldrefInfo -> {
                    constant.value = FieldRef.newFieldRef(constantPool, constantInfo)
                }
                is ConstantMethodrefInfo -> {
                    constant.value = MethodRef.newMethodRef(constantPool, constantInfo)
                }
                is ConstantInterfaceMethodrefInfo -> {
                    constant.value = InterfaceMethodRef.newInterfaceMethodRef(constantPool, constantInfo)
                }
            }
        }
    }
}