package org.romadis.pattern.student

import org.romadis.observer.ObserveSubject
import org.romadis.observer.Observer
import org.romadis.pattern.Data_list
import org.romadis.student.Student
import org.romadis.student.Student_short

class Data_list_student_short(students: List<Student_short>) : Data_list<Student_short>(students), ObserveSubject {

    constructor(full_students: List<Student>) : this(students = full_students.map { Student_short(it) })

    override val observers: MutableList<Observer> = mutableListOf()

    override fun getEntityFields(): List<String> {
        return listOf("ID", "FIO", "Git", "Contact")
    }

    override fun getDataRow(entity: Student_short): List<Any> {
        return listOf(entity.id, entity.lastNameInitials, entity.git, entity.contact) as List<Any>
    }

}