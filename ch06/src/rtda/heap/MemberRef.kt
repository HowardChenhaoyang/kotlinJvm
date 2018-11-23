package rtda.heap

import classfile.ConstantMemberrefInfo

open class MemberRef : SymRef() {
    var name: String = ""
        private set
    var descriptor: String = ""
        private set

    fun copyMemberRefInfo(constantMemberrefInfo: ConstantMemberrefInfo) {
        className = constantMemberrefInfo.className()
        val (name, type) = constantMemberrefInfo.nameAndDescriptor()
        this.name = name
        this.descriptor = type
    }
}