package org.romadis.adapter

import org.romadis.dto.StudentFilter
import org.romadis.pattern.student.Data_list_student_short
import org.romadis.strategy.Student_list_file
import org.romadis.student.Student

class StudentListFileAdapter(
    private val studentListFile: Student_list_file,
    var studentFilter: StudentFilter? = null
) : StudentListInterface {

    override fun getStudentById(id: Int): Student? {
        return try {
            studentListFile.findById(id)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun getKNStudentShortList(k: Int, n: Int): Data_list_student_short {
        return studentListFile.get_k_n_student_short_list(k, n) as Data_list_student_short
    }

    override fun addStudent(student: Student): Int {
        studentListFile.add(student)
        return student.id
    }

    override fun updateStudent(student: Student): Boolean {
        getStudentById(student.id) ?: return false
        studentListFile.replaceById(student, student.id)
        return true
    }

    override fun deleteStudent(id: Int): Boolean {
        getStudentById(id) ?: return false
        studentListFile.removeById(id)
        return true
    }

    override fun getStudentCount(): Int {
        return studentListFile.get_student_short_count()
    }
}