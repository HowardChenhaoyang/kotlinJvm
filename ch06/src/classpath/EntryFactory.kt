package classpath


typealias ClassZipEntry = ZipEntry

fun newEntry(path: String): Entry {
    if (path.contains(pathListSeparator)) {
        return CompositeEntry.createCompositeEntry(path)
    }
    if (path.endsWith("*")) {
        return WildCardEntry(path)
    }
    if (path.endsWith(".jar")
            || path.endsWith(".JAR")
            || path.endsWith(".zip")
            || path.endsWith(".ZIP")) {
        return ClassZipEntry(path)
    }
    return DirEntry(path)
}