package instructions.references

import instructions.base.Index16Instruction
import instructions.base.invokeMethod
import rtda.Frame
import rtda.heap.*

// Invoke instance method;
// special handling for superclass, private, and instance initialization method invocations
class InvokeSpecial : Index16Instruction() {
    override fun execute(frame: Frame) {
        val currentClass = frame.method!!.clazz
        val constantPool = currentClass.constantPool!!
        val methodRef = constantPool.getConstant(index) as MethodRef
        val resolvedClass = methodRef.resolvedClass()
        val resolvedMethod = methodRef.resolvedMethod()
        if (resolvedMethod.name == "<init>" && resolvedMethod.clazz != resolvedClass) {
            throw NoSuchMethodError()
        }
        if (resolvedMethod.isStatic()) {
            throw IncompatibleClassChangeError()
        }
        val ref = frame.operandStack!!.getRefFromTop(resolvedMethod.argSlotCount - 1) ?: throw NullPointerException()

        //如果该方法是protected的，并且是当前类父类的成员方法，但没有在当前类所属的运行时package中声明，那么ref要么为当前类，要么为当前类的子类。
        if (resolvedMethod.isProtected() &&
                resolvedMethod.clazz.isSuperClassOf(currentClass) &&
                resolvedMethod.clazz.getPackageName() != currentClass.getPackageName() &&
                ref.clazz != currentClass &&
                !ref.clazz!!.isSubClassOf(currentClass)) {
            throw IllegalAccessError()
        }
        var methodToBeInvoked: Method? = resolvedMethod
        if (currentClass.isSuper() && resolvedClass.isSuperClassOf(currentClass) && resolvedMethod.name != "<init>") {
            methodToBeInvoked = lookupMethodInClass(currentClass.superClass!!, methodRef.name, methodRef.descriptor)
        }
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
            throw AbstractMethodError()
        }
        invokeMethod(frame, methodToBeInvoked)
    }
}