package rtda

class Thread {
    var pc: Int = -1
    lateinit var stack: Stack

    fun pushFrame(frame: Frame) {
        stack.push(frame)
    }

    fun popFrame(): Frame = stack.pop()

    fun currentFrame() = stack.top()

    companion object {
        fun newThread(): Thread {
            return Thread().apply {
                stack = Stack.newStack(1024)
            }
        }
    }
}