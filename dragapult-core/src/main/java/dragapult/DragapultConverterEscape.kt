package dragapult

class DragapultConverterEscape : DragapultConverter<String, String> {

    override fun convert(input: String) = input
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\'", "\\\'")
        .replace("\b", "\\b")
        .replace("\u000c", "\\f")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t")

}