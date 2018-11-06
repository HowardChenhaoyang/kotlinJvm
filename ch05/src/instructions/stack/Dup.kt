package instructions.stack

import instructions.base.NoOperandsInstruction
import rtda.Frame


/*
bottom -> top
[...][c][b][a]
             \_
               |
               V
[...][c][b][a][a]
*/
class Dup : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val slot = frame.operandStack!!.popSlot()
        frame.operandStack!!.pushSlot(slot)
        frame.operandStack!!.pushSlot(slot.copy())
    }
}


/*
bottom -> top
[...][c][b][a]
          __/
         |
         V
[...][c][a][b][a]
*/
class DupX1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack!!.popSlot()
        val slot2 = stack.popSlot()
        stack.pushSlot(slot1)
        stack.pushSlot(slot2)
        stack.pushSlot(slot1)
    }
}


/*
bottom -> top
[...][c][b][a]
       _____/
      |
      V
[...][a][c][b][a]
*/
class DupX2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack!!.popSlot()
        val slot2 = stack.popSlot()
        val slot3 = stack.popSlot()
        stack.pushSlot(slot1)
        stack.pushSlot(slot3)
        stack.pushSlot(slot2)
        stack.pushSlot(slot1)
    }
}


/*
bottom -> top
[...][c][b][a]____
          \____   |
               |  |
               V  V
[...][c][b][a][b][a]
*/
class Dup2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack!!.popSlot()
        val slot2 = stack.popSlot()
        stack.pushSlot(slot2)
        stack.pushSlot(slot1)
        stack.pushSlot(slot2.copy())
        stack.pushSlot(slot1.copy())
    }
}


/*
bottom -> top
[...][c][b][a]
       _/ __/
      |  |
      V  V
[...][b][a][c][b][a]
*/
class Dup2X1 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack!!.popSlot()
        val slot2 = stack.popSlot()
        val slot3 = stack.popSlot()
        stack.pushSlot(slot2)
        stack.pushSlot(slot1)
        stack.pushSlot(slot3)
        stack.pushSlot(slot2.copy())
        stack.pushSlot(slot1.copy())
    }
}

/*
bottom -> top
[...][d][c][b][a]
       ____/ __/
      |   __/
      V  V
[...][b][a][d][c][b][a]
*/
class Dup2X2 : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack!!.popSlot()
        val slot2 = stack.popSlot()
        val slot3 = stack.popSlot()
        val slot4 = stack.popSlot()
        stack.pushSlot(slot2)
        stack.pushSlot(slot1)
        stack.pushSlot(slot4)
        stack.pushSlot(slot3)
        stack.pushSlot(slot2.copy())
        stack.pushSlot(slot1.copy())
    }
}