package instructions.base

class MethodDescriptorParse {
    var raw: String? = null
    var offset: Int = 0
    var parsed: MethodDescriptor = MethodDescriptor()

    private fun parse(descriptor: String): MethodDescriptor {
        raw = descriptor
        startParams()
        parseParamTypes()
        endParams()
        parseReturnType()
        finish()
        return parsed
    }

    private fun finish() {
        if (offset != raw?.length) causePanic()
    }

    private fun parseReturnType() {
        if (readChar() == 'V') {
            parsed.returnType = "V"
            return
        }
        unreadChar()
        val t = parseFieldType()
        if (t.isNullOrEmpty()) {
            causePanic()
        }
        parsed.returnType = t
    }

    private fun endParams() {
        if (readChar() != ')') {
            causePanic()
        }
    }

    private fun parseParamTypes() {
        while (true) {
            val t = parseFieldType()
            if (t.isNullOrEmpty()) {
                break
            } else {
                parsed.addParameterType(t)
            }
        }
    }

    private fun startParams() {
        if (readChar() != '(') {
            causePanic()
        }
    }

    private fun readChar(): Char {
        val b = raw!![offset]
        offset++
        return b
    }

    private fun causePanic() {
        if (offset != raw?.length) {
            throw RuntimeException("BAD descriptor: $raw")
        }
    }

    private fun unreadChar() {
        offset--
    }

    private fun parseFieldType(): String {
        val char = readChar()
        return when (char) {
            'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z' -> char.toString()
            'L' -> parseObjectType()
            '[' -> parseArrayType()
            else -> {
                unreadChar()
                ""
            }
        }
    }

    private fun parseArrayType(): String {
        val arrStart = offset - 1
        parseFieldType()
        val arrEnd = offset
        return raw!!.substring(arrStart, arrEnd)
    }

    private fun parseObjectType(): String {
        val semicolonIndex = raw!!.indexOf(';')
        if (semicolonIndex == -1) {
            causePanic()
            return ""
        }
        val objStart = offset - 1
        val objEnd = offset + semicolonIndex + 1
        offset = objEnd
        return raw!!.substring(objStart, objEnd)
    }

    companion object {
        fun parseMethodDescriptor(descriptor: String): MethodDescriptor {
            return MethodDescriptorParse().parse(descriptor)
        }
    }
}