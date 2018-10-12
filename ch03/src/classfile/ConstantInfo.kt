package classfile

sealed class ConstantInfo {
    companion object {
        fun readConstantInfo(classReader: ClassReader, constantPool: ConstantPool):ConstantInfo {

        }
    }
}
class ConstantLongInfo: ConstantInfo() {

}
class ConstantDoubleInfo: ConstantInfo() {

}