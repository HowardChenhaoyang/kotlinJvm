package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame
import rtda.heap.*

class GetStatic : Index16Instruction() {
    override fun execute(frame: Frame) {
        val cp = frame.method!!.clazz.constantPool!!
        val fieldRef = cp.getConstant(index) as FieldRef
        val field = fieldRef.resolvedField()
        val clazz = field.clazz
        if (!field.isStatic()) {
            throw IncompatibleClassChangeError()
        }
        val descriptor = field.descriptor
        val slotId = field.slotId
        val staticVars = clazz.staticVars!!
        val operandStack = frame.operandStack!!
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' -> {
                operandStack.pushInt(staticVars.getInt(slotId))
            }
            'F' -> {
                operandStack.pushFloat(staticVars.getFloat(slotId))
            }
            'J' -> {
                operandStack.pushLong(staticVars.getLong(slotId))
            }
            'D' -> {
                operandStack.pushDouble(staticVars.getDouble(slotId))
            }
            'L', '[' -> {
                operandStack.pushRef(staticVars.getRef(slotId))
            }
            else -> {
                // todo
            }
        }
    }
}