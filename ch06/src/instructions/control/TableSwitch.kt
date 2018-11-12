package instructions.control

import instructions.base.BytecodeReader
import instructions.base.Instruction
import instructions.base.branch
import rtda.Frame

/*
tableswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
lowbyte1
lowbyte2
lowbyte3
lowbyte4
highbyte1
highbyte2
highbyte3
highbyte4
jump offsets...
*/
class TableSwitch : Instruction {
    var defaultOffset = 0
    var low = 0
    var high = 0
    var jumpOffsets = intArrayOf()
    override fun fetchOperands(reader: BytecodeReader) {
        reader.skipPadding()
        defaultOffset = reader.readInt32()
        low = reader.readInt32()
        high = reader.readInt32()
        val jumpOffsetsCount = high - low + 1
        jumpOffsets = reader.readInt32s(jumpOffsetsCount)
    }

    override fun execute(frame: Frame) {
        val index = frame.operandStack!!.popInt()
        val offset = if (index in low..high) {
            jumpOffsets[index - low]
        } else {
            defaultOffset
        }
        branch(frame, offset)
    }
}