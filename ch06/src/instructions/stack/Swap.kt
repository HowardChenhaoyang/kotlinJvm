package instructions.stack

import instructions.base.NoOperandsInstruction
import rtda.Frame

/*
bottom -> top
[...][c][b][a]
          \/
          /\
         V  V
[...][c][a][b]
*/
class Swap : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack!!.popSlot()
        val slot2 = stack.popSlot()
        stack.pushSlot(slot1)
        stack.pushSlot(slot2)
    }
}