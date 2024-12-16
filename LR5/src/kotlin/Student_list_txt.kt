package main.src
import Data_list
import java.io.File

class Student_list_txt : StudentListInterface {
    private val students: MutableList<Student> = mutableListOf()

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

    // Private helper methods for reading and writing files
    private fun loadStudents() {
        val filePath = "students.txt"
        students.clear()
        students.addAll(readFromFile(filePath))
    }

    private fun saveStudents() {
        val filePath = "students.txt"
        writeToFile(filePath, students)
    }

    fun readFromFile(filePath: String): List<Student> {
        val students = mutableListOf<Student>()
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        file.forEachLine { line ->
            try {
                val student = Student(line)
                students.add(student)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: \"$line\"")
            }
        }
        return students
    }

    fun writeToFile(filePath: String, students: List<Student>) {
        val file = File(filePath)
        file.bufferedWriter().use { writer ->
            students.forEach { student ->
                writer.write(
                    "${student.id};${student.lastName};${student.firstName};${student.middleName ?: ""};" +
                            "${student.phone ?: ""};${student.telegram ?: ""};${student.email ?: ""};${student.git ?: ""}"
                )
                writer.newLine()
            }
        }
    }
}