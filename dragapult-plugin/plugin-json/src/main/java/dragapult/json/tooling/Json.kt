package dragapult.json.tooling

import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

fun File.jsonObject(): JSONObject {
    val tokener = JSONTokener(reader())
    return JSONObject(tokener)
}