package classpath

import java.io.File
import java.util.*
import java.util.jar.JarFile
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

typealias JavaZipEntry = ZipEntry

class ZipEntry(private val path: String) : Entry {

    init {
        checkPath(path)
    }

    override fun readClass(className: String): EntryReadClassResult? {
        val zipFile = if (className.endsWith(".zip")
                || className.endsWith(".ZIP")) {
            ZipFile(getFileName(path, ""))
        } else {
            JarFile(getFileName(path, ""))
        }
        var entry: JavaZipEntry? = null
        val enum: Enumeration<out JavaZipEntry> = zipFile.entries()
        while (enum.hasMoreElements()) {
            val next = enum.nextElement()
            if (next.name == className) {
                entry = next
                break
            }
        }
        if (entry == null) return null
        val classEntry = zipFile.getEntry(className)
        return EntryReadClassResult().apply {
            this.bytes = zipFile.getInputStream(classEntry).use { it.readBytes() }
            this.entry = this@ZipEntry
        }
    }

    override fun toString(): String = File(path).absolutePath

}