package rtda

class Frame {
    var next: Frame? = null
    var localVars: LocalVars? = null

    companion object {
        fun newFrame(maxLocals: Int, maxStack: Int): Frame {
            return Frame().apply {
                localVars = LocalVarsFactory.newLocalVars(maxLocals)
            }
        }
    }
}