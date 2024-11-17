import main.src.Student
import main.src.Student_short

class StudentsList(private var strategy: StudentListInterface) {
    private val students: MutableList<Student> = mutableListOf()

    // Чтение из файла
    fun readStudents(filePath: String) {
        //students.clear()
        students.addAll(strategy.readFromFile(filePath))
    }

    // Запись в файл
    fun writeStudents(filePath: String) {
        strategy.writeToFile(filePath, students)
    }

    // Получение объекта класса Student по ID
    fun getStudentById(id: Int): Student? {
        return students.find { it.id == id }
    }

    // Получение списка k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list<Student_short> {
        val startIndex = (n - 1) * k
        val endIndex = startIndex + k
        val studentShortList = students.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(students.size)).map { Student_short(it) }
        return Data_list(studentShortList)
    }

    // Сортировка элементов по ФИО
    fun sortStudents() {
        students.sortBy { it.getInfo() }
    }

    // Добавление объектов класса Student в список
    fun addStudent(student: Student) {
        students.add(student)
    }

    // Замена элементов списка по ID
    fun replaceStudentById(id: Int, newStudent: Student) {
        val newStud = newStudent
        newStud.id = id
        val ind = students.indexOf(this.students.find { it.id == id })
        if (ind != -1) students[ind] = newStud
        else this.addStudent(newStudent)
    }

    // Удаление элемента списка по ID
    fun removeStudentById(id: Int) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) {
            students.removeAt(index)
        } else {
            throw NoSuchElementException("Студент с ID $id не найден.")
        }
    }

    // Получение количества элементов
    fun getStudentShortCount(): Int {
        return students.size
    }

    fun getStudents(): List<Student> {
        return students
    }
}