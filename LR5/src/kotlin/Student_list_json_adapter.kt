import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import main.src.*
import java.io.File

class Student_list_json_adapter(private val jsonInstance: Student_list_txt) : Student_List_Adapter {
    private val students: MutableList<Student> = mutableListOf()

    init {
        students.addAll(jsonInstance.readFromFile("student_json.json"))
    }

    override fun getStudentById(id: Int): Student? {
        return students.find { it.id == id }
    }

    override fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        val startIndex = (n - 1) * k
        val endIndex = startIndex + k
        val sublist = students.subList(startIndex, endIndex.coerceAtMost(students.size))
        return Data_list(sublist.map { Student_short(it) })
    }

    override fun addStudent(student: Student): Int {
        val newId = (students.maxOfOrNull { it.id } ?: 0) + 1
        val newStudent = student.copy(id = newId)
        students.add(newStudent)
        jsonInstance.writeToFile("student_json.json", students)
        return newId
    }

    override fun replaceStudentById(id: Int, newStudent: Student) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) {
            students[index] = newStudent.copy(id = id)
        } else {
            addStudent(newStudent)
        }
        jsonInstance.writeToFile("student_json.json", students)
    }

    override fun removeStudentById(id: Int) {
        students.removeIf { it.id == id }
        jsonInstance.writeToFile("student_json.json", students)
    }

    override fun getStudentShortCount(): Int {
        return students.size
    }
}