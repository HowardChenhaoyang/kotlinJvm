package instructions.control

import instructions.base.BytecodeReader
import instructions.base.Instruction
import instructions.base.branch
import rtda.Frame

/*
lookupswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
npairs1
npairs2
npairs3
npairs4
match-offset pairs...
*/
class LookUpSwitch : Instruction {
    var defaultOffset = 0
    var npairs = 0
    var matchOffsets = intArrayOf()
    override fun fetchOperands(reader: BytecodeReader) {
        reader.skipPadding()
        defaultOffset = reader.readInt32()
        npairs = reader.readInt32()
        matchOffsets = reader.readInt32s(npairs * 2)
    }

    override fun execute(frame: Frame) {
        val key = frame.operandStack!!.popInt()
        for (i in 0 until npairs * 2 step 2) {
            if (matchOffsets[i] == key) {
                branch(frame, matchOffsets[i + 1])
                return
            }
        }
        branch(frame, defaultOffset)
    }
}