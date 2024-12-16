import main.src.Student
import main.src.StudentListInterface
import main.src.Student_short

class Student_list(private var strategy: StudentListInterface) {
    fun setStrategy(newStrategy: StudentListInterface) {
        strategy = newStrategy
    }

    fun getStudentById(id: Int): Student? = strategy.getStudentById(id)

    fun get_k_n_student_short_list(k: Int, n: Int): Data_list<Student_short> =
        strategy.get_k_n_student_short_list(k, n)

    fun addStudent(student: Student) = strategy.addStudent(student)

    fun updateStudent(id: Int, student: Student) = strategy.updateStudent(id, student)

    fun deleteStudent(id: Int) = strategy.deleteStudent(id)

    fun getStudentShortCount(): Int = strategy.getStudentShortCount()
}