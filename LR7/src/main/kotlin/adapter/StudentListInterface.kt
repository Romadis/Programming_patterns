package org.romadis.adapter

import org.romadis.dto.StudentFilter
import org.romadis.pattern.student.Data_list_student_short
import org.romadis.student.Student

interface StudentListInterface {

    fun getStudentById(id: Int): Student?

    fun getKNStudentShortList(k: Int, n: Int): Data_list_student_short

    fun addStudent(student: Student): Int

    fun updateStudent(student: Student): Boolean

    fun deleteStudent(id: Int): Boolean

    fun getStudentCount(): Int
}