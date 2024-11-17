package main.src
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Students_list_JSON(filePath: String) : StudentsList(filePath) {

    // Чтение из файла
    override fun readFromFile() {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        val jsonString = file.readText()
        students.addAll(Json.decodeFromString<List<Student>>(jsonString))
    }

    // Запись в файл
    override fun writeToFile() {
        val file = File("output_json.json")
        val jsonString = Json.encodeToString(students)
        file.writeText(jsonString)
    }
}