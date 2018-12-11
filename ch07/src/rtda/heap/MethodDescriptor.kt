package instructions.base

class MethodDescriptor {
    var parameterTypes: Array<String>? = null
    var returnType: String? = null
    private val parameterTypeList = mutableListOf<String>()
    fun addParameterType(str: String) {
        parameterTypeList.add(str)
        parameterTypes = parameterTypeList.toTypedArray()
    }
}