package main.src
import java.io.File

class Students_list_txt(filePath: String) : StudentsList(filePath) {

    // Чтение файла
    override fun readFromFile() {
        val file = File(filePath)

        if (!file.exists() || !file.canRead()) {
            throw IllegalArgumentException("Файл недоступен: $filePath")
        }

        file.forEachLine { line ->
            try {
                val student = Student(line)
                Student.students.add(student)
            } catch (e: IllegalArgumentException) {
                println("Ошибка: \"$line\"")
            }
        }
    }

    // Запись в файл
    public override fun writeToFile() {
        val file = File("output.txt")
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
}