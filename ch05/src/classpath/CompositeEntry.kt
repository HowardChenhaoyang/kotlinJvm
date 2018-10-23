package classpath


class CompositeEntry private constructor() : Entry {

    private lateinit var entrys: List<Entry>

    override fun readClass(className: String): EntryReadClassResult? {
        for (entry in entrys) {
            try {
                return entry.readClass(className)
            } catch (e: Exception) {
            }
        }
        return null
    }

    override fun toString(): String = entrys.joinToString(pathListSeparator) { it.toString() }

    companion object {
        fun createCompositeEntry(path: String) = CompositeEntry().apply {
            entrys = path.split(pathListSeparator).mapNotNull {
                try {
                    newEntry(it)
                } catch (e: Exception) {
                    error(e)
                    null
                }
            }
        }

        fun createCompositeEntry(entrys: List<Entry>) = CompositeEntry().apply { this.entrys = entrys }
    }
}