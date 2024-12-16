import main.src.Student
import main.src.Student_short

class Students_List_DB_Adapter(private val dbInstance: Student_list_DB) : Student_List_Adapter {
    override fun getStudentById(id: Int): Student? {
        return dbInstance.getStudentById(id)
    }

    override fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        return dbInstance.get_k_n_student_short_list(n, k)
    }

    override fun addStudent(student: Student): Int {
        dbInstance.addStudent(student)
        return dbInstance.getTotalStudents()
    }

    override fun replaceStudentById(id: Int, newStudent: Student) {
        dbInstance.updateStudent(id, newStudent)
    }

    override fun removeStudentById(id: Int) {
        dbInstance.deleteStudent(id)
    }

    override fun getStudentShortCount(): Int {
        return dbInstance.getTotalStudents()
    }
}