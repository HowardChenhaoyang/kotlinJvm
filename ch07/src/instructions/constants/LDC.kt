package instructions.constants

import instructions.base.Index16Instruction
import instructions.base.Index8Instruction
import rtda.Frame

class LDC : Index8Instruction() {
    override fun execute(frame: Frame) {
        _ldc(frame, index)
    }
}

class LDC_W : Index16Instruction() {
    override fun execute(frame: Frame) {
        _ldc(frame, index)
    }
}

class LDC2_W : Index16Instruction() {
    override fun execute(frame: Frame) {
        val operandStack = frame.operandStack!!
        val cp = frame.method!!.clazz.constantPool!!
        val c = cp.getConstant(index)
        when (c) {
            is Long -> {
                operandStack.pushLong(c)
            }
            is Double -> {
                operandStack.pushDouble(c)
            }
            else -> {
                throw ClassFormatError()
            }
        }
    }
}

private fun _ldc(frame: Frame, index: Int) {
    val operandStack = frame.operandStack!!
    val cp = frame.method!!.clazz.constantPool!!
    val c = cp.getConstant(index)
    when (c) {
        is Int -> {
            operandStack.pushInt(c)
        }
        is Float -> {
            operandStack.pushFloat(c)
        }
        else -> {
            println(" c is " + c)
            TODO()
        }
    }
}