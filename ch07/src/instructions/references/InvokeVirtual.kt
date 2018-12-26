package instructions.references

import instructions.base.Index16Instruction
import instructions.base.invokeMethod
import rtda.Frame
import rtda.OperandStack
import rtda.heap.MethodRef
import rtda.heap.isSubClassOf
import rtda.heap.isSuperClassOf
import rtda.heap.lookupMethodInClass

class InvokeVirtual : Index16Instruction() {
    override fun execute(frame: Frame) {
        val currentClass = frame.method!!.clazz
        val cp = currentClass.constantPool!!
        val methodRef = cp.getConstant(index) as MethodRef
        val resolvedMethod = methodRef.resolvedMethod()
        if (resolvedMethod.isStatic()) {
            throw IncompatibleClassChangeError()
        }

        val ref = frame.operandStack!!.getRefFromTop(resolvedMethod.argSlotCount - 1)
        if (ref == null) {
            if (methodRef.name == "println") {
                println(frame.operandStack!!, methodRef.descriptor)
                return
            }
            throw NullPointerException()
        }

        //如果该方法是protected的，并且是当前类父类的成员方法，但没有在当前类所属的运行时package中声明，那么ref要么为当前类，要么为当前类的子类。
        if (resolvedMethod.isProtected() &&
                resolvedMethod.clazz.isSuperClassOf(currentClass) &&
                resolvedMethod.clazz.getPackageName() != currentClass.getPackageName() &&
                ref.clazz != currentClass &&
                !ref.clazz!!.isSubClassOf(currentClass)) {
            throw IllegalAccessError()
        }
        val methodToBeInvoked = lookupMethodInClass(ref.clazz, methodRef.name, methodRef.descriptor)
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw AbstractMethodError()
        }
        invokeMethod(frame, methodToBeInvoked)
    }

    private fun println(operandStack: OperandStack, descriptor: String) {
        when (descriptor) {
            "(Z)V" -> println("${operandStack.popInt() != 0}")
            "(F)V" -> println("${operandStack.popFloat()}")
            "(J)V" -> println("${operandStack.popLong()}")
            "(D)V" -> println("${operandStack.popDouble()}")
            "(C)V", "(I)V", "(B)V", "(S)V" -> println("${operandStack.popInt()}")
            else -> throw RuntimeException("println $descriptor")
        }
        operandStack.popRef()
    }
}