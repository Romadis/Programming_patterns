import main.src.Student
import main.src.Student_list_txt

fun main() {
    val dbAdapter = Students_List_DB_Adapter(Student_list_DB.getInstance())
    val studentListDB = StudentList(dbAdapter)

    val txtAdapter = Students_List_txt_adapter(Student_list_txt())
    val studentListTxt = StudentList(txtAdapter)

    println(studentListTxt.getStudentById(6))

    for (i in 0 until studentListTxt.getStudentShortCount() + 1) {
        val student = studentListTxt.getStudentById(i)
        if (student != null) {
            studentListDB.addStudent(student)
        }
    }


}