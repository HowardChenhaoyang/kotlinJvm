package instructions.references

import instructions.base.Index16Instruction
import instructions.base.initClass
import rtda.Frame
import rtda.heap.ClassRef

class New : Index16Instruction() {
    override fun execute(frame: Frame) {
        val cp = frame.method!!.clazz.constantPool!!
        val classRef = cp.getConstant(index) as ClassRef
        val clazz = classRef.resolvedClass()
        if (!clazz.initStarted){
            frame.revertNextPC()
            initClass(frame.thread, clazz)
        }
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw InstantiationError()
        }
        val ref = clazz.newObject()
        frame.operandStack!!.pushRef(ref)
    }
}