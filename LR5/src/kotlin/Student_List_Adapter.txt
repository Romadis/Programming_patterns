import main.src.Student
import main.src.Student_short

interface Student_List_Adapter {
    fun getStudentById(id: Int): Student?
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short>
    fun addStudent(student: Student): Int
    fun replaceStudentById(id: Int, newStudent: Student)
    fun removeStudentById(id: Int)
    fun getStudentShortCount(): Int
}