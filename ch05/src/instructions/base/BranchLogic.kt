package instructions.base

import rtda.Frame

fun branch(frame: Frame, offset: Int) {
    val pc = frame.thread.pc
    frame.nextPc = pc + offset
}