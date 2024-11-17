import main.src.*

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

    // с txt файлом
    val txtStrategy = Student_list_txt()
    val studentsListTxt = StudentsList(txtStrategy)
    studentsListTxt.readStudents("students.txt")
    println(studentsListTxt.getStudentShortCount())

    studentsListTxt.sortStudents()
    println(studentsListTxt.getStudents())

    studentsListTxt.getStudentById(1)

    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
    studentsListTxt.replaceStudentById(3, student6)
    println(studentsListTxt.getStudents())

    studentsListTxt.writeStudents("output.txt")

    studentsListTxt.removeStudentById(3)
    println(studentsListTxt.getStudents())


    // с json файлом
//    val jsonStrategy = Student_list_JSON()
//    val studentsListJson = StudentsList(jsonStrategy)
//    studentsListJson.readStudents("student_json.json")
//
//    println(studentsListJson.getStudentShortCount())
//
//    studentsListJson.sortStudents()
//    println(studentsListJson.getStudents())
//
//    studentsListJson.getStudentById(1)
//
//    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
//    studentsListJson.replaceStudentById(3, student6)
//    println(studentsListJson.getStudents())
//
//    studentsListJson.writeStudents("output.json")
//
//    studentsListJson.removeStudentById(3)
//    println(studentsListJson.getStudents())


    // с yaml файлом
//    val yamlStrategy = Student_list_YAML()
//    val studentsListYaml = StudentsList(yamlStrategy)
//    studentsListYaml.readStudents("student_yaml.yaml")
//    println(studentsListYaml.getStudentShortCount())
//
//    studentsListYaml.sortStudents()
//    println(studentsListYaml.getStudents())
//
//    studentsListYaml.getStudentById(1)
//
//    val student6 = Student(6,"Романов","Владислав","Витальевич", phone = "+79186916943", git="https://github.com/Romadis")
//    studentsListYaml.replaceStudentById(3, student6)
//    println(studentsListYaml.getStudents())
//
//    studentsListYaml.writeStudents("output.yaml")
//
//    studentsListYaml.removeStudentById(3)
//    println(studentsListYaml.getStudents())
}