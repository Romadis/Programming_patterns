import main.src.Student

interface StudentListInterface {
    fun readFromFile(filePath: String): List<Student>
    fun writeToFile(filePath: String, students: List<Student>)
}