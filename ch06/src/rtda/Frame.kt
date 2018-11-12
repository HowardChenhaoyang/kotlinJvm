package rtda


class Frame {
    var next: Frame? = null
    var localVars: LocalVars? = null
    var operandStack: OperandStack? = null
    lateinit var thread: Thread
    var nextPc: Int = 0

    companion object {
        fun newFrame(thread: Thread, maxLocals: Int, maxStack: Int): Frame {
            return Frame().apply {
                this.thread = thread
                localVars = LocalVarsFactory.newLocalVars(maxLocals)
                operandStack = OperandStack.newOperandStack(maxStack)
            }
        }
    }
}