package instructions.control

import instructions.base.NoOperandsInstruction
import rtda.Frame

// Return void from method
class Return : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        frame.thread.popFrame()
    }
}

// Return reference from method
class AReturn : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val thread = frame.thread
        val currentFrame = thread.popFrame()
        val invokerFrame = thread.topFrame()
        val ref = currentFrame.operandStack!!.popRef()
        invokerFrame.operandStack!!.pushRef(ref)
    }
}

// Return double from method
class DReturn : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val thread = frame.thread
        val currentFrame = thread.popFrame()
        val invokerFrame = thread.topFrame()
        val value = currentFrame.operandStack!!.popDouble()
        invokerFrame.operandStack!!.pushDouble(value)
    }
}

// Return float from method
class FReturn : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val thread = frame.thread
        val currentFrame = thread.popFrame()
        val invokerFrame = thread.topFrame()
        val value = currentFrame.operandStack!!.popFloat()
        invokerFrame.operandStack!!.pushFloat(value)
    }
}


// Return int from method
class IReturn : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val thread = frame.thread
        val currentFrame = thread.popFrame()
        val invokerFrame = thread.topFrame()
        val value = currentFrame.operandStack!!.popInt()
        invokerFrame.operandStack!!.pushInt(value)
    }
}

// Return int from method
class LReturn : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val thread = frame.thread
        val currentFrame = thread.popFrame()
        val invokerFrame = thread.topFrame()
        val value = currentFrame.operandStack!!.popLong()
        invokerFrame.operandStack!!.pushLong(value)
    }
}


