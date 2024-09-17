fun main() {
    val student = Student(
        id = 1,
        surname = "Романов",
        name = "Владислав",
        patronymic = "Витальевич",
        phone = "+79186916942",
        telegram = "@romadisich",
        email = "vladcool155@gmail.com",
        git = "https://github.com/Romadis"
    )

    println("ФИО: ${student.surname} ${student.name} ${student.patronymic}")
    println("Телефон: ${student.phone}")
    println("Телеграм: ${student.telegram}")
    println("Почта: ${student.email}")
    println("Гит: ${student.git}")

    student.phone = "+78005553535"
    println("Обновили номер телефона: ${student.phone}")
}