import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options
import java.util.*


class Cmd {
    var helpFlag: Boolean? = null
    var versionFlag: Boolean? = null
    var classpath: String? = null
    var clazz: String? = null
    var args: Array<String>? = null
    var xJreOption:String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cmd

        if (helpFlag != other.helpFlag) return false
        if (versionFlag != other.versionFlag) return false
        if (classpath != other.classpath) return false
        if (clazz != other.clazz) return false
        if (!Arrays.equals(args, other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = helpFlag?.hashCode() ?: 0
        result = 31 * result + (versionFlag?.hashCode() ?: 0)
        result = 31 * result + (classpath?.hashCode() ?: 0)
        result = 31 * result + (clazz?.hashCode() ?: 0)
        result = 31 * result + (args?.let { Arrays.hashCode(it) } ?: 0)
        return result
    }

    companion object {

        fun wrapperOptions(): Options {
            return Options().apply {
                addOption("help", false, "print help message")
                addOption("?", false, "print help message")
                addOption("version", false, "print version and edit")
                addOption("classpath", true, "classpath")
                addOption("cp", true, "classpath")
                addOption("class", true, "class")
                addOption("args", true, "args")
                addOption("Xjre", true, "path to jre")
            }
        }

        fun parseCmd(args: Array<String>): Cmd {
            val options = wrapperOptions()
            val commandLine = DefaultParser().parse(options, args)
            return Cmd().apply {
                this.clazz = commandLine.getOptionValue("class")
                this.args = commandLine.getOptionValues("args")
                this.helpFlag = commandLine.hasOption("help") || commandLine.hasOption("?")
                this.versionFlag = commandLine.hasOption("version")
                this.classpath = if (commandLine.hasOption("classpath")) commandLine.getOptionValue("classpath") else commandLine.getOptionValue("cp")
                this.xJreOption = commandLine.getOptionValue("Xjre")
            }
        }
        fun printUsage() {
            val options = wrapperOptions()
            val hf = HelpFormatter()
            hf.printHelp("commond [options] -class class -args args", "", options, "")
        }
    }
}