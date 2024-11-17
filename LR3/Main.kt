import main.src.*

fun main() {
    val input = "students.txt"
    val studentsFromFile = Students_list_txt(input)

    println(Student.students.toString())
    println(studentsFromFile.getStudentShortCount())

    studentsFromFile.sortStudents()
    println(Student.students.toString())

    studentsFromFile.getStudentById(1)

    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
    val student1 = "6,Романов,Владислав,Витальевич,+79186916943,null,null,https://github.com/Romadis"
    studentsFromFile.replaceStudentById(3, student6)
    println(Student.students.toString())

    studentsFromFile.writeToFile()

    studentsFromFile.removeStudentById(3)
    println(Student.students.toString())


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
//    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
//    studentsFromFileJSON.replaceStudentById(3, student6)
//    println(studentsFromFileJSON.getStudent())
//
//    studentsFromFileJSON.writeToJson()
//
//    studentsFromFileJSON.removeStudentById(3)
//    println(studentsFromFileJSON.getStudent())


//    val yaml = "student_yaml.yaml"
//    val studentsFromFileYAML = Students_list_YAML(yaml)
//
//    println(studentsFromFileYAML.getStudent())
//    println(studentsFromFileYAML.getStudentShortCount())
//
//    studentsFromFileYAML.sortStudents()
//    println(studentsFromFileYAML.getStudent())
//
//    studentsFromFileYAML.getStudentById(1)
//
//    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
//    studentsFromFileYAML.replaceStudentById(3, student6)
//    println(studentsFromFileYAML.getStudent())
//
//    studentsFromFileYAML.writeToFile()
//
//    studentsFromFileYAML.removeStudentById(3)
//    println(studentsFromFileYAML.getStudent())
}