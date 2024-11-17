package main.src
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File

class Students_list_YAML(filePath: String) : StudentsList(filePath) {
    private val objectMapper = ObjectMapper(YAMLFactory())

    // Чтение всех значений из файла
    override fun readFromFile() {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        students.addAll(objectMapper.readValue(
            file,
            objectMapper.typeFactory.constructCollectionType(MutableList::class.java, Student::class.java)
        ))
    }

    // Запись всех значений в файл
    public override fun writeToFile() {
        val file = File("output.yaml")
        objectMapper.writeValue(file, students)
    }
}