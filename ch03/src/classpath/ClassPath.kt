package classpath

import Cmd
import java.io.File

class ClassPath {
    private lateinit var bootClassPath: Entry
    private lateinit var extClassPath: Entry
    private lateinit var userClassPath: Entry

    fun readClass(className: String): EntryReadClassResult {
        var copyClassName = className
        if (!className.endsWith(".class")) {
            copyClassName = "$className.class"
        }
        return bootClassPath.readClass(copyClassName)
                ?: extClassPath.readClass(copyClassName)
                ?: userClassPath.readClass(copyClassName) ?: throw Exception("class $copyClassName not found")
    }

    companion object {
        fun parse(cmd: Cmd): ClassPath {
            val jre = parseBootAndExtClassPath(cmd.xJreOption)
            return ClassPath().apply {
                bootClassPath = newEntry("$jre/lib/*")
                extClassPath = newEntry("$jre/lib/ext/*")
                userClassPath = newEntry(parseUserClassPath(cmd.classpath))
            }
        }

        private fun parseUserClassPath(classpath: String?): String {
            if (classpath.isNullOrEmpty()) {
                return File(".").absolutePath
            }
            return File(classpath!!).absolutePath
        }

        private fun parseBootAndExtClassPath(jreOption: String?): String {
            if (!jreOption.isNullOrEmpty() && File(jreOption).exists()) {
                return File(jreOption!!).absolutePath
            }
            if (File("./jre").exists()) {
                return File("./jre").absolutePath
            }
            val javaHome = System.getenv("JAVA_HOME")
            if (!javaHome.isNullOrEmpty()) {
                return File(javaHome, "jre").absolutePath
            }
            throw Exception("can not find jre folder")
        }

    }
}