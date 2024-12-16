import main.src.Student
import main.src.StudentListInterface
import main.src.Student_short

abstract class Student_list_DB_Adapter(private val db: Student_list_DB) : StudentListInterface {
    override fun getStudentById(id: Int): Student? = db.getStudentById(id)

    override fun get_k_n_student_short_list(k: Int, n: Int): Data_list<Student_short> = db.get_k_n_student_short_list(k, n)

    override fun addStudent(student: Student) = db.addStudent(student)

    override fun updateStudent(id: Int, student: Student) = db.updateStudent(id, student)

    override fun deleteStudent(id: Int) = db.deleteStudent(id)

    override fun getStudentShortCount(): Int = db.getTotalStudents()
}