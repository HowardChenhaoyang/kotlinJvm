package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame
import rtda.heap.*

class GetField : Index16Instruction() {
    override fun execute(frame: Frame) {
        val cp = frame.method!!.clazz.constantPool!!
        val fieldRef = cp.getConstant(index) as FieldRef
        val field = fieldRef.resolvedField()
        if (field.isStatic()) {
            throw IncompatibleClassChangeError()
        }
        val operandStack = frame.operandStack!!
        val ref = operandStack.popRef() ?: throw NullPointerException()
        val descriptor = field.descriptor
        val slotId = field.slotId
        val fields = ref.fields!!
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' -> {
                operandStack.pushInt(fields.getInt(slotId))
            }
            'F' -> {
                operandStack.pushFloat(fields.getFloat(slotId))
            }
            'J' -> {
                operandStack.pushLong(fields.getLong(slotId))
            }
            'D' -> {
                operandStack.pushDouble(fields.getDouble(slotId))
            }
            'L', '[' -> {
                operandStack.pushRef(fields.getRef(slotId))
            }
            else -> {
                // todo
            }
        }
    }
}