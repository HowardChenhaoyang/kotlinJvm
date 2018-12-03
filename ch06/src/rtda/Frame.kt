package rtda

import rtda.heap.Method


class Frame {
    var next: Frame? = null
    var localVars: LocalVars? = null
    var operandStack: OperandStack? = null
    lateinit var thread: Thread
    var nextPc: Int = 0
    var method: Method? = null

    companion object {
        fun newFrame(thread: Thread, method: Method): Frame {
            val (maxStack, maxLocals) = method
            return Frame().apply {
                this.thread = thread
                this.method = method
                localVars = LocalVarsFactory.newLocalVars(maxLocals)
                operandStack = OperandStack.newOperandStack(maxStack)
            }
        }
    }
}