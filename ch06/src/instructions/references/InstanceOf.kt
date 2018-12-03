package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame
import rtda.heap.ClassRef

class InstanceOf : Index16Instruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack!!
        val ref = stack.popRef()
        if (ref == null) {
            stack.pushInt(0)
            return
        }
        val cp = frame.method!!.clazz.constantPool!!
        val classRef = cp.getConstant(index) as ClassRef
        val clazz = classRef.resolvedClass()
        if (ref.isInstanceOf(clazz)) {
            stack.pushInt(1)
        } else {
            stack.pushInt(0)
        }
    }
}