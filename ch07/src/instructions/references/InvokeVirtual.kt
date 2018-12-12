package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame
import rtda.heap.MethodRef

class InvokeVirtual : Index16Instruction() {
    override fun execute(frame: Frame) {
        val cp = frame.method!!.clazz.constantPool!!
        val methodRef = cp.getConstant(index) as MethodRef
        if (methodRef.name == "println") {
            val operandStack = frame.operandStack!!
            when (methodRef.descriptor) {
                "(Z)V" -> println("${operandStack.popInt() != 0}")
                "(F)V" -> println("${operandStack.popFloat()}")
                "(J)V" -> println("${operandStack.popLong()}")
                "(D)V" -> println("${operandStack.popDouble()}")
                "(C)V", "(I)V", "(B)V", "(S)V" -> println("${operandStack.popInt()}")
                else -> throw RuntimeException("println ${methodRef.descriptor}")
            }
            operandStack.popRef()
        }
    }
}