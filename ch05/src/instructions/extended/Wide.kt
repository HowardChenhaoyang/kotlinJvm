package instructions.extended

import instructions.base.BytecodeReader
import instructions.base.Instruction
import instructions.loads.*
import instructions.math.IInc
import instructions.stores.*
import rtda.Frame

class Wide : Instruction {
    private lateinit var modifiedInstruction: Instruction
    override fun fetchOperands(reader: BytecodeReader) {
        val opCode = reader.readUint8()
        modifiedInstruction = when (opCode) {
            0x15 -> {
                ILoad().apply { index = reader.readUInt16() }
            }
            0x16 -> {
                LLoad().apply { index = reader.readUInt16() }
            }
            0x17 -> {
                FLoad().apply { index = reader.readUInt16() }
            }
            0x18 -> {
                DLoad().apply { index = reader.readUInt16() }
            }
            0x19 -> {
                ALoad().apply { index = reader.readUInt16() }
            }
            0x36 -> {
                IStore().apply { index = reader.readUInt16() }
            }
            0x37 -> {
                LStore().apply { index = reader.readUInt16() }
            }
            0x38 -> {
                FStore().apply { index = reader.readUInt16() }
            }
            0x39 -> {
                DStore().apply { index = reader.readUInt16() }
            }
            0x3a -> {
                AStore().apply { index = reader.readUInt16() }
            }
            0x84 -> {
                IInc().apply { index = reader.readUInt16(); constValue = reader.readInt16() }
            }
            0xa9 -> {
                TODO("0xa9")
            }
            else -> {
                throw RuntimeException("Unsupported opcode: 0xa9!")
            }
        }
    }

    override fun execute(frame: Frame) {
        modifiedInstruction.execute(frame)
    }
}