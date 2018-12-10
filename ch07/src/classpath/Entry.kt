package classpath

import java.io.File

@JvmField
val pathListSeparator: String = File.pathSeparator

class EntryReadClassResult {
    lateinit var bytes: ByteArray
    lateinit var entry: Entry
}

interface Entry {
    fun readClass(className: String): EntryReadClassResult?
    override fun toString(): String
    fun checkPath(path: String) {
        if (!File(path).exists()) {
            throw IllegalArgumentException("file $path not exist")
        }
    }

    fun getFileName(path: String, className: String): String {
        return File(path,className).absolutePath
    }
}