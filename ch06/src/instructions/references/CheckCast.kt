package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame
import rtda.heap.ClassRef

class CheckCast : Index16Instruction() {
    override fun execute(frame: Frame) {
        val operandStack = frame.operandStack!!
        val ref = operandStack.popRef()
        operandStack.pushRef(ref)
        if (ref == null) return
        val cp = frame.method!!.clazz.constantPool!!
        val classRef = cp.getConstant(index) as ClassRef
        val clazz = classRef.resolvedClass()
        if (!ref.isInstanceOf(clazz)) {
            throw ClassCastException()
        }
    }
}