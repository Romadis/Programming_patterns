package main.src
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Student_list_JSON : StudentListInterface{
    private val json = Json { ignoreUnknownKeys = true }

    override fun readFromFile(filePath: String): List<Student> {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        val jsonString = file.readText()
        //Student.students = Json.decodeFromString(jsonString)
        //return Student.students
        return json.decodeFromString(jsonString)
    }

    override fun writeToFile(filePath: String, students: List<Student>) {
        val file = File(filePath)
        val jsonString = json.encodeToString(students)
        file.writeText(jsonString)
    }
}