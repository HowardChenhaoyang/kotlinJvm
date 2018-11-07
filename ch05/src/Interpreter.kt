import classfile.MemberInfo
import instructions.InstructionFactory
import instructions.base.BytecodeReader
import rtda.Frame
import rtda.Thread

class Interpreter {
    companion object {
        fun interpret(memberInfo: MemberInfo) {
            val codeAttr = memberInfo.codeAttribute() ?: return
            val maxLocals = codeAttr.maxLocals
            val maxStack = codeAttr.maxStack
            val byteCode = codeAttr.code
            val thread = Thread.newThread()
            val frame = Frame.newFrame(thread, maxLocals, maxStack)
            thread.pushFrame(frame)
            try {
                loop(thread, byteCode!!)
            } catch (e: Exception) {
                for (localVar in frame.localVars!!) {
                    println("localVars ${localVar.num}")
                }
                for (slot in frame.operandStack?.slots!!) {
                    println("operandStack ${slot.num}")
                }
            }
        }

        private fun loop(thread: Thread, byteCode: ByteArray) {
            val frame = thread.popFrame()
            val reader = BytecodeReader()
            while (true) {
                val pc = frame.nextPc
                thread.pc = pc
                reader.reset(byteCode, pc)
                val opCode = reader.readUint8()
                val instruction = InstructionFactory.newInstruction(opCode)
                instruction.fetchOperands(reader)
                frame.nextPc = reader.pc
                instruction.execute(frame)
            }
        }
    }
}