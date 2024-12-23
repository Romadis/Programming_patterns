import main.src.*
import java.sql.DriverManager

fun main() {
//    val input = "students.txt"
//    val studentsFromFile = Students_list_txt(input)
//
//    println(Student.students.toString())
//    println(studentsFromFile.getStudentShortCount())
//
//    studentsFromFile.sortStudents()
//    println(Student.students.toString())
//
//    studentsFromFile.getStudentById(1)
//
//    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
//    val student1 = "6,Романов,Владислав,Витальевич,+79186916943,null,null,https://github.com/Romadis"
//    studentsFromFile.replaceStudentById(3, student6)
//    println(Student.students.toString())
//
//    studentsFromFile.writeToFile()
//
//    studentsFromFile.removeStudentById(3)
//    println(Student.students.toString())


//    val json = "student_json.json"
//    val studentsFromFileJSON = Students_list_JSON(json)
//
//    println(studentsFromFileJSON.getStudent())
//    println(studentsFromFileJSON.getStudentShortCount())
//
//    studentsFromFileJSON.sortStudents()
//    println(studentsFromFileJSON.getStudent())
//
//    studentsFromFileJSON.getStudentById(1)
//
//    val url = "jdbc:postgresql://localhost:5432/postgres"
//    val user = "postgres"
//    val password = "postgres"  
//
//    try {
//        val connection = DriverManager.getConnection(url, user, password)
//        println("Successfully connection!")
//        connection.close()
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    DB.executeSelect()

      val studentDB = Student_list_DB.getInstance()
      println(studentDB.getStudentById(2))
      println(studentDB.getTotalStudents())

      val student = Student(6,"Кривцов","Никита","Сергеевич", telegram = "@nikitka34", git="https://github.com/nik777")
      studentDB.addStudent(student)
      println(studentDB.getStudentById(6))
}