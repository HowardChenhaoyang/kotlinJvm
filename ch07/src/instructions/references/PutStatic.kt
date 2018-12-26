package instructions.references

import instructions.base.Index16Instruction
import instructions.base.initClass
import rtda.Frame
import rtda.heap.*

class PutStatic : Index16Instruction() {
    override fun execute(frame: Frame) {
        val currentMethod = frame.method!!
        val currentClass = currentMethod.clazz
        val cp = currentClass.constantPool!!
        val fieldRef = cp.getConstant(index) as FieldRef
        val field = fieldRef.resolvedField()
        val clazz = field.clazz
        if (!clazz.initStarted){
            frame.revertNextPC()
            initClass(frame.thread, clazz)
        }
        if (!field.isStatic()) {
            throw IncompatibleClassChangeError()
        }
        if (field.isFinal()) {
            if (currentClass != clazz || currentMethod.name != "<clinit>") {
                throw IllegalAccessError()
            }
        }
        val descriptor = field.descriptor
        val slotId = field.slotId
        val staticVars = clazz.staticVars!!
        val operandStack = frame.operandStack!!
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' -> {
                staticVars.setInt(slotId, operandStack.popInt())
            }
            'F' -> {
                staticVars.setFloat(slotId, operandStack.popFloat())
            }
            'J' -> {
                staticVars.setLong(slotId, operandStack.popLong())
            }
            'D' -> {
                staticVars.setDouble(slotId, operandStack.popDouble())
            }
            'L', '[' -> {
                staticVars.setRef(slotId, operandStack.popRef())
            }
            else -> {
                // TODO()
            }
        }
    }
}