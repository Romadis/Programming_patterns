package main.src
import Data_list
import java.io.File
import main.src.Student.Examination

class Students_list_txt(private val filePath: String) {
    private val students : MutableList<Student> = mutableListOf()
    init {
        read_from_txt()
    }
    
    // Читаем все значения из файла
    fun read_from_txt(): List<SuperStudent> {
        val file = File(filePath)
        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл не существует: $filePath")
        }
        //val students = mutableListOf<SuperStudent>()
        file.forEachLine { line ->
            try{
                val student = Student(line)
                Student.students.add(student)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: \"$line\"")
            }
        }
        return Student.students
    }

    // Записываем все значения в файл
    fun write_to_txt() {
        val file = File("src/output.txt")
        file.bufferedWriter().use { writer ->
            Student.students.forEach { student ->
                writer.write(
                    "${student.id};${student.lastName};${student.firstName};${student.middleName ?: ""};" +
                            "${student.phone ?: ""};${student.telegram ?: ""};${student.email ?: ""};${student.git ?: ""}"
                )
                writer.newLine()
            }
        }
    }

    // Получаем объект класса Student по идентификатору
    fun getStudentById(id: Int): Student? {
        return Student.students.find { it.id == id }
    }

    // Получаем список k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        val startIndex = (n - 1) * k
        val endIndex = startIndex + k
        val studentShortList = Student.students.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(students.size)).map { Student_short(it) }
        return Data_list(studentShortList)
    }

    // Сортируем элементы по набору ФИО
    fun sortStudents() {
        Student.students.sortBy { it.getInfo() }
    }

    // Добавляем объект класса Student в список
    fun addStudent(student: Student, id: Int = students.maxOf { it.id } + 1) {
        val newStudent = student
        newStudent.id = id
        Student.students.add(newStudent)
    }

    // Заменяем элемент списка по идентификатору
    fun replaceStudentById(id: Int, newStudent: Student) {
        val newStud = newStudent
        newStud.id = id
        val ind = Student.students.indexOf(this.students.find { it.id == id })
        if (ind != -1) Student.students[ind] = newStud
        else this.addStudent(newStudent, id)
    }

    // Удаляем элемент списка по идентификатору
    fun removeStudentById(id: Int) {
        val index = Student.students.indexOfFirst { it.id == id }
        if (index != -1) {
            Student.students.removeAt(index)
        } else {
            throw NoSuchElementException("Студент с ID $id не найден.")
        }
    }

    // Получаем количество элементов
    fun getStudentShortCount(): Int {
        return Student.students.size
    }
}