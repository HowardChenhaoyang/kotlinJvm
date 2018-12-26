import instructions.InstructionFactory
import instructions.base.BytecodeReader
import rtda.Frame
import rtda.Thread
import rtda.heap.Method

class Interpreter {
    companion object {
        fun interpret(method: Method) {
            val thread = Thread.newThread()
            val frame = Frame.newFrame(thread, method)
            thread.pushFrame(frame)
            loop(thread, method.code!!)
        }

        private fun loop(thread: Thread, byteCode: ByteArray) {
            val reader = BytecodeReader()
            while (true) {
                val frame = thread.topFrame()
                val pc = frame.nextPc
                thread.pc = pc
                reader.reset(byteCode, pc)
                val opCode = reader.readUint8()
                val instruction = InstructionFactory.newInstruction(opCode)
                instruction.fetchOperands(reader)
                frame.nextPc = reader.pc
                instruction.execute(frame)
                if (thread.isStackEmpty()) {
                    break
                }
            }
        }
    }
}