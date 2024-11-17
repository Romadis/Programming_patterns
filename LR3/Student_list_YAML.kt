package main.src
import StudentListInterface
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File

class Student_list_YAML : StudentListInterface {
    private val objectMapper = ObjectMapper(YAMLFactory())

    override fun readFromFile(filePath: String): List<Student> {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        return objectMapper.readValue(
            file,
            objectMapper.typeFactory.constructCollectionType(MutableList::class.java, Student::class.java)
        )
    }

    override fun writeToFile(filePath: String, students: List<Student>) {
        val file = File(filePath)
        objectMapper.writeValue(file, students)
    }
}