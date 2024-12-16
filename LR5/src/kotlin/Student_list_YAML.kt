package main.src
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File

class Student_list_YAML() : StudentListInterface {
    private val objectMapper = ObjectMapper(YAMLFactory()).apply {
        registerModule(KotlinModule())
    }

    override fun readFromFile(filePath: String): List<Student> {
        val file = File(filePath)
        return if (file.exists()) {
            objectMapper.readValue(
                file,
                objectMapper.typeFactory.constructCollectionType(List::class.java, Student::class.java)
            )
        } else {
            emptyList()
        }
    }

    override fun writeToFile(filePath: String, students: List<Student>) {
        val file = File(filePath)
        objectMapper.writeValue(file, students)
    }
}