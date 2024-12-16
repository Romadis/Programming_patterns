package main.src
import Data_list
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.*
import kotlinx.serialization.decodeFromString
import main.src.Student.Examination
import java.io.File

class Student_list_JSON : StudentListInterface {
    private val students: MutableList<Student> = mutableListOf()
    private val json = Json { ignoreUnknownKeys = true }

    override fun getStudentById(id: Int): Student? {
        loadStudents()
        return students.find { it.id == id }
    }

    override fun get_k_n_student_short_list(k: Int, n: Int): Data_list<Student_short> {
        loadStudents()
        val startIndex = (n - 1) * k
        val endIndex = startIndex + k
        val studentShortList = students.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(students.size))
            .map { Student_short(it) }
        return Data_list(studentShortList)
    }

    override fun addStudent(student: Student) {
        loadStudents()
        students.add(student)
        saveStudents()
    }

    override fun updateStudent(id: Int, student: Student) {
        loadStudents()
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) {
            students[index] = student
        } else {
            throw NoSuchElementException("Student with ID $id not found.")
        }
        saveStudents()
    }

    override fun deleteStudent(id: Int) {
        loadStudents()
        students.removeIf { it.id == id }
        saveStudents()
    }

    override fun getStudentShortCount(): Int {
        loadStudents()
        return students.size
    }

    private fun loadStudents() {
        val filePath = "students.json"
        val file = File(filePath)
        if (file.exists()) {
            val jsonString = file.readText()
            students.clear()
            students.addAll(json.decodeFromString(jsonString))
        }
    }

    private fun saveStudents() {
        val filePath = "students.json"
        val file = File(filePath)
        val jsonString = json.encodeToString(students)
        file.writeText(jsonString)
    }
}