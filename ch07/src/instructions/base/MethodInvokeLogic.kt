package instructions.base

import rtda.Frame
import rtda.heap.Method
import rtda.setSlot

fun invokeMethod(invokerFrame: Frame, method: Method) {
    val thread = invokerFrame.thread
    val newFrame = thread.newFrame(method)
    thread.pushFrame(newFrame)
    val argSlotCount = method.argSlotCount
    if (argSlotCount > 0) {
        for (i in (argSlotCount - 1 downTo 0)) {
            val slot = invokerFrame.operandStack!!.popSlot()
            newFrame.localVars!!.setSlot(i, slot)
        }
    }
    if (method.isNative()) {
        if (method.name == "registerNatives") {
            thread.popFrame()
        } else {
            throw RuntimeException("native method: ${method.clazz.name} ${method.name} ${method.descriptor}")
        }
    }
}