package main.src
import Data_list
import com.charleskorn.kaml.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File

class Students_list_YAML(private val filePath: String) {
    private var students: MutableList<Student> = mutableListOf()
    private val objectMapper = ObjectMapper(YAMLFactory())

    init {
        readFromYaml()
    }

    // Чтение всех значений из файла
    fun readFromYaml(): List<Student> {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        students = objectMapper.readValue(
            file,
            objectMapper.typeFactory.constructCollectionType(MutableList::class.java, Student::class.java)
        )
        return students
    }

    // Запись всех значений в файл
    fun writeToYaml() {
        val file = File("output.yaml")
        objectMapper.writeValue(file, students)
    }

    // Получение объекта класса Student по ID
    fun getStudentById(id: Int): Student? {
        return Student.students.find { it.id == id }
    }

    // Получение списка k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        val startIndex = (n - 1) * k
        val endIndex = startIndex + k
        val studentShortList = Student.students.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(students.size)).map { Student_short(it) }
        return Data_list(studentShortList)
    }

    // Сортировка элементов по ФИО
    fun sortStudents() {
        Student.students.sortBy { it.getInfo() }
    }

    // Добавление объектов класса Student в список
    fun addStudent(student: Student, id: Int = students.maxOf { it.id } + 1) {
        val newStudent = student
        newStudent.id = id
        Student.students.add(newStudent)
    }

    // Замена элементов списка по ID
    fun replaceStudentById(id: Int, newStudent: Student) {
        val newStud = newStudent
        newStud.id = id
        val ind = Student.students.indexOf(this.students.find { it.id == id })
        if (ind != -1) Student.students[ind] = newStud
        else this.addStudent(newStudent, id)
    }

    // Удаление элемента списка по ID
    fun removeStudentById(id: Int) {
        val index = Student.students.indexOfFirst { it.id == id }
        if (index != -1) {
            Student.students.removeAt(index)
        } else {
            throw NoSuchElementException("Студент с ID $id не найден.")
        }
    }

    // Получение количества элементов
    fun getStudentShortCount(): Int {
        return Student.students.size
    }

    fun getStudent(): List<SuperStudent> {
        return students.toList()
    }
}