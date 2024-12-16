import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import main.src.*
import java.io.File

class Student_list_json_adapter(private val jsonHandler: Student_list_JSON) : StudentListInterface {
    private val students: MutableList<Student> = mutableListOf()
    private val json = Json { ignoreUnknownKeys = true }

    fun readFromFile(filePath: String): List<Student> {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        val jsonString = file.readText()
        //Student.students = Json.decodeFromString(jsonString)
        //return Student.students
        return json.decodeFromString(jsonString)
    }

    fun writeToFile(filePath: String) {
        val file = File(filePath)
        val jsonString = json.encodeToString(students)
        file.writeText(jsonString)
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
        saveStudents("students.json")
    }

    override fun updateStudent(id: Int, student: Student) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) students[index] = student
        saveStudents("students.json")
    }

    override fun deleteStudent(id: Int) {
        students.removeIf { it.id == id }
        saveStudents("students.json")
    }

    override fun getStudentShortCount(): Int = students.size

    private fun saveStudents(filePath: String) {
        val file = File(filePath)
        val jsonString = json.encodeToString(students)
        file.writeText(jsonString)
    }
}