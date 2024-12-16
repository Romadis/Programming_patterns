import main.src.Student
import main.src.StudentListInterface
import main.src.Student_list_txt
import main.src.Student_short

class Student_list_txt_adapter(private val txtHandler: Student_list_txt) : StudentListInterface {
    private val students: MutableList<Student> = mutableListOf()

    init {
        loadStudents()
    }

    private fun loadStudents() {
        students.clear()
        students.addAll(txtHandler.readFromFile("students.txt"))
    }

    private fun saveStudents() {
        txtHandler.writeToFile("students.txt", students)
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
        saveStudents()
    }

    override fun updateStudent(id: Int, student: Student) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) students[index] = student
        saveStudents()
    }

    override fun deleteStudent(id: Int) {
        students.removeIf { it.id == id }
        saveStudents()
    }

    override fun getStudentShortCount(): Int = students.size
}