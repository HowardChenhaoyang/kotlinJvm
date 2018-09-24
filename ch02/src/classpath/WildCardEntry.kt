package classpath

import java.io.File

class WildCardEntry(path: String) : Entry {
    private val compositeEntry: CompositeEntry

    init {
        val dir = path.dropLast(1)
        checkPath(dir)
        if (!File(dir).isDirectory) {
            throw IllegalArgumentException("$dir is not dir")
        }
        val entrys: List<Entry> = File(dir).listFiles().mapNotNull {
            if (it.absolutePath.endsWith(".jar")
                    || it.absolutePath.endsWith(".JAR")) {
                ZipEntry(it.absolutePath)
            } else {
                null
            }
        }
        compositeEntry = CompositeEntry.createCompositeEntry(entrys)

    }

    override fun readClass(className: String): EntryReadClassResult? {
        return compositeEntry.readClass(className)
    }

    override fun toString(): String = compositeEntry.toString()

}