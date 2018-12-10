package instructions.references

import instructions.base.Index16Instruction
import rtda.Frame
import rtda.heap.*

class PutField : Index16Instruction() {
    override fun execute(frame: Frame) {
        val currentMethod = frame.method!!
        val currentClass = currentMethod.clazz
        val cp = currentClass.constantPool!!
        val fieldRef = cp.getConstant(index) as FieldRef
        val field = fieldRef.resolvedField()
        if (field.isStatic()) {
            throw IncompatibleClassChangeError()
        }
        if (field.isFinal()) {
            if (currentClass != field.clazz || currentMethod.name != "<init>") {
                throw IllegalAccessError()
            }
        }
        val descriptor = field.descriptor
        val slotId = field.slotId
        val operandStack = frame.operandStack!!
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' -> {
                val value = operandStack.popInt()
                val ref = operandStack.popRef() ?: throw NullPointerException()
                ref.fields!!.setInt(slotId, value)
            }
            'F' -> {
                val value = operandStack.popFloat()
                val ref = operandStack.popRef() ?: throw NullPointerException()
                ref.fields!!.setFloat(slotId, value)
            }
            'J' -> {
                val value = operandStack.popLong()
                val ref = operandStack.popRef() ?: throw NullPointerException()
                ref.fields!!.setLong(slotId, value)
            }
            'D' -> {
                val value = operandStack.popDouble()
                val ref = operandStack.popRef() ?: throw NullPointerException()
                ref.fields!!.setDouble(slotId, value)
            }
            'L','[' -> {
                val value = operandStack.popRef()
                val ref = operandStack.popRef() ?: throw NullPointerException()
                ref.fields!!.setRef(slotId, value)
            }
            else -> {
                // todo
            }
        }
    }
}