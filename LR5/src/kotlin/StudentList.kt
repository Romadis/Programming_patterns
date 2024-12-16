import main.src.Student
import main.src.Student_short

class StudentList(private val adapter: Student_List_Adapter) {
    fun getStudentById(id: Int): Student? {
        return adapter.getStudentById(id)
    }

    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        return adapter.get_k_n_student_short_list(n, k)
    }

    fun addStudent(student: Student): Int {
        return adapter.addStudent(student)
    }

    fun replaceStudentById(id: Int, newStudent: Student) {
        adapter.replaceStudentById(id, newStudent)
    }

    fun removeStudentById(id: Int) {
        adapter.removeStudentById(id)
    }

    fun getStudentShortCount(): Int {
        return adapter.getStudentShortCount()
    }
}