package instructions.references

import instructions.base.Index16Instruction
import instructions.base.initClass
import instructions.base.invokeMethod
import rtda.Frame
import rtda.heap.MethodRef

class InvokeStatic: Index16Instruction(){
    override fun execute(frame: Frame) {
        val cp = frame.method!!.clazz.constantPool!!
        val methodRef = cp.getConstant(index) as MethodRef
        val resolvedMethod = methodRef.resolvedMethod()
        if (!resolvedMethod.isStatic()){
            throw IncompatibleClassChangeError()
        }
        val clazz = resolvedMethod.clazz
        if (!clazz.initStarted){
            frame.revertNextPC()
            initClass(frame.thread, clazz)
        }
        invokeMethod(frame, resolvedMethod)
    }
}