import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import main.src.*
import java.io.File

class Student_list_yaml_adapter(private val yamlHandler: Student_list_YAML) : StudentListInterface {
    private val students: MutableList<Student> = mutableListOf()
    private val objectMapper = ObjectMapper(YAMLFactory()).apply {
        registerModule(KotlinModule())
    }

    fun readFromFile(filePath: String): List<Student> {
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

    fun writeToFile(filePath: String, students: List<Student>) {
        val file = File(filePath)
        objectMapper.writeValue(file, students)
    }

    override fun getStudentById(id: Int): Student? = students.find { it.id == id }

    override fun get_k_n_student_short_list(k: Int, n: Int): Data_list<Student_short> {
        val start = (n - 1) * k
        val end = start + k
        val shortList = students.subList(start.coerceAtLeast(0), end.coerceAtMost(students.size))
            .map { Student_short(it) }
        return Data_list(shortList)
    }

    override fun addStudent(student: Student) {
        students.add(student)
        writeToFile("students.yaml", students)
    }

    override fun updateStudent(id: Int, student: Student) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) students[index] = student
        writeToFile("students.yaml", students)
    }

    override fun deleteStudent(id: Int) {
        students.removeIf { it.id == id }
        writeToFile("students.yaml", students)
    }

    override fun getStudentShortCount(): Int = students.size
}