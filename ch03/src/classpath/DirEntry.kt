package classpath

import java.io.File

class DirEntry(private val path: String) : Entry {
    init {
        checkPath(path)
    }

    override fun readClass(className: String): EntryReadClassResult? {
        val fileName = getFileName(path, className)
        return try {
            EntryReadClassResult().apply {
                bytes = File(fileName).readBytes()
                entry = this@DirEntry
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun toString(): String = File(path).absolutePath

}