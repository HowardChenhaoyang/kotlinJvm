package rtda.heap

import classfile.ConstantClassInfo

class ClassRef : SymRef() {
    companion object {
        fun newClassRef(constantPool: ConstantPool, classInfo: ConstantClassInfo): ClassRef {
            val ref = ClassRef()
            ref.cp = constantPool
            ref.className = classInfo.name
            return ref
        }
    }
}