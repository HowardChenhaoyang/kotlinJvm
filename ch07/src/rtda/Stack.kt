package rtda

class Stack {
    var maxSize: Int = 0
    var size: Int = 0
    private var top: Frame? = null

    fun push(frame: Frame) {
        if (size >= maxSize) throw StackOverflowError()
        frame.next = top
        top = frame
        size++
    }

    fun pop(): Frame {
        if (top == null) throw RuntimeException("jvm stack is empty!")
        val top = top
        this.top = top!!.next
        top.next = null
        return top
    }

    fun top(): Frame {
        if (top == null) throw RuntimeException("jvm stack is empty!")
        return top!!
    }

    companion object {
        fun newStack(maxSize: Int): Stack {
            return Stack().apply { this.maxSize = maxSize }
        }
    }
}