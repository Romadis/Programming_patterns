package main.src

class Student_short(
    id: Int,
    lastName: String,
    firstName: String,
    middleName: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
):SuperStudent(id, lastName, firstName, middleName, phone, telegram, email, git) {
    // Конструктор, принимающий объект класса Student
    constructor(student: Student) : this(
        id         = student.id,
        lastName   = student.lastName,
        firstName  = student.firstName,
        middleName = student.middleName.toString(),
        phone      = student.phone,
        telegram   = student.telegram,
        email      = student.email,
        git        = student.git
    )
    // Конструктор, принимающий ID и строку с информацией
    constructor(id: Int, studentString: String) : this(
        id         = id,
        lastName   = studentString.split(",")[0],
        firstName  = studentString.split(",")[1],
        middleName = studentString.split(",").getOrNull(2).toString(),
        phone      = studentString.split(",").getOrNull(3),
        telegram   = studentString.split(",").getOrNull(4),
        email      = studentString.split(",").getOrNull(5),
        git        = studentString.split(",").getOrNull(6)
    )
    override fun toString(): String {
        return "Student_short(id=$id, ${getInfo()})"
    }
}