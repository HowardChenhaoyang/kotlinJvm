package instructions.base

import rtda.Thread
import rtda.heap.Class

fun initClass(thread: Thread, clazz: Class) {
    clazz.startInit()
    scheduleClinit(thread, clazz)
    initSuperClass(thread, clazz)
}

private fun scheduleClinit(thread: Thread, clazz: Class) {
    val clinit = clazz.getClinitMethod()
    if (clinit != null) {
        val newFrame = thread.newFrame(clinit)
        thread.pushFrame(newFrame)
    }
}

private fun initSuperClass(thread: Thread, clazz: Class) {
    if (!clazz.isInterface()) {
        val superClass = clazz.superClass
        if (superClass != null && !superClass.initStarted) {
            initClass(thread, superClass)
        }
    }
}