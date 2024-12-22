package org.romadis.controllers

import org.romadis.strategy.Student_list
import org.romadis.strategy.Student_list_DB
import org.romadis.student.Student

class StudentCreateController(
    val studentListController: Student_list_controller,
    val studentList: Student_list
): StudentFormController() {

    constructor(studentListController: Student_list_controller): this(studentListController, Student_list(Student_list_DB()))

    override fun saveProcessedStudent(student: Student): String {
        val id = studentList.addStudent(student)
        if (id > 0) {
            studentListController.refresh_data()
            return "Студент добавлен!"
        } else {
            return "Ошибка при добавлении студента."
        }
    }
}