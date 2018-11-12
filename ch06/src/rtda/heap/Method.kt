package rtda.heap

import classfile.MemberInfo

class Method {
    var name:String = ""
    var descriptor:String = ""
    fun isStatic():Boolean{

    }
    companion object {
        fun newMethod(clazz: Class, cfMethods: Array<MemberInfo>):Array<Method> {

        }
    }
}