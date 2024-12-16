package main.src
import Data_list
import main.src.Student
import main.src.Student_short

interface StudentListInterface {
    //fun readFromFile(filePath: String): List<Student>
    //fun writeToFile(filePath: String, students: List<Student>)
    fun getStudentById(id: Int): Student?
    fun get_k_n_student_short_list(k: Int, n: Int): Data_list<Student_short>
    fun addStudent(student: Student)
    fun updateStudent(id: Int, student: Student)
    fun deleteStudent(id: Int)
    fun getStudentShortCount(): Int
}