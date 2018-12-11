package rtda

import rtda.heap.Method

class Thread {
    var pc: Int = -1
    lateinit var stack: Stack

    fun pushFrame(frame: Frame) {
        stack.push(frame)
    }

    fun newFrame(method: Method):Frame{
        return Frame.newFrame(this, method)
    }

    fun popFrame(): Frame = stack.pop()

    fun topFrame() = stack.top()

    companion object {
        fun newThread(): Thread {
            return Thread().apply {
                stack = Stack.newStack(1024)
            }
        }
    }
}