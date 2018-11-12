package instructions.constants

import instructions.base.NoOperandsInstruction
import rtda.Frame

class Nop : NoOperandsInstruction() {
    override fun execute(frame: Frame) {
    }
}