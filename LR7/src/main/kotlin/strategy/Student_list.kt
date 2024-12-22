package org.romadis.strategy

import org.romadis.adapter.StudentListInterface
import org.romadis.dto.StudentFilter
import org.romadis.pattern.student.Data_list_student_short
import org.romadis.student.Student

class Student_list(private val studentSource: StudentListInterface) {

    fun getStudentById(id: Int): Student? {
        return studentSource.getStudentById(id)
    }

    fun getKNStudentShortList(k: Int, n: Int, studentFilter: StudentFilter? = null): Data_list_student_short {
        return studentSource.getKNStudentShortList(k, n)
    }

    fun addStudent(student: Student): Int {
        return studentSource.addStudent(student)
    }

    fun updateStudent(student: Student): Boolean {
        return studentSource.updateStudent(student)
    }

    fun deleteStudent(id: Int): Boolean {
        return studentSource.deleteStudent(id)
    }

    fun getStudentCount(): Int {
        return studentSource.getStudentCount()
    }
}