package rtda


class Frame {
    var next: Frame? = null
    var localVars: LocalVars? = null
    var operandStack: OperandStack? = null

    companion object {
        fun newFrame(maxLocals: Int, maxStack: Int): Frame {
            return Frame().apply {
                localVars = LocalVarsFactory.newLocalVars(maxLocals)
                operandStack = OperandStack.newOperandStack(maxStack)
            }
        }
    }
}