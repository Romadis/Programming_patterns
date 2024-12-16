import main.src.Student
import main.src.Student_short
import java.sql.*

class Student_list_DB private constructor(private val connection: Connection) {
    companion object {
        private var instance: Student_list_DB? = null

        fun getInstance(): Student_list_DB {
            if (instance == null) {
                val connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
                instance = Student_list_DB(connection)
            }
            return instance!!
        }
    }

    // Получить объект класса Student по ID
    fun getStudentById(id: Int): Student? {
        val sql = "SELECT * FROM student WHERE id = ?"
        val statement = connection.prepareStatement(sql)
        statement.setInt(1, id)
        val resultSet = statement.executeQuery()

        return if (resultSet.next()) {
            Student(
                id = resultSet.getInt("id"),
                lastName = resultSet.getString("lastname"),
                firstName = resultSet.getString("firstname"),
                middleName = resultSet.getString("middlename"),
                phone = resultSet.getString("phone"),
                telegram = resultSet.getString("telegram"),
                email = resultSet.getString("email"),
                git = resultSet.getString("git")
            )
        } else {
            null
        }
    }

    // Получить список k по счету n объектов класса Student_short
    fun get_k_n_student_short_list(k: Int, n: Int): Data_list<Student_short> {
        val sql = "SELECT * FROM student LIMIT ? OFFSET ?"
        val statement = connection.prepareStatement(sql)
        statement.setInt(1, k)
        statement.setInt(2, (n - 1) * k)  // Оffset: (n-1) * k
        val resultSet = statement.executeQuery()
        val studentShortList = mutableListOf<Student_short>()
        while (resultSet.next()) {
            studentShortList.add(
                Student_short(
                    id = resultSet.getInt("id"),
                    lastName = resultSet.getString("lastname"),
                    firstName = resultSet.getString("firstname"),
                    middleName = resultSet.getString("middlename"),
                    phone = resultSet.getString("phone"),
                    telegram = resultSet.getString("telegram"),
                    email = resultSet.getString("email"),
                    git = resultSet.getString("git")
                )
            )
        }
        return Data_list(studentShortList)
    }

    // Добавить объект класса Student в таблицу
    fun addStudent(student: Student) {
        val sql = "INSERT INTO student (lastname, firstname, middlename, phone, telegram, email, git) VALUES (?, ?, ?, ?, ?, ?, ?)"
        val statement = connection.prepareStatement(sql)
        statement.setString(1, student.lastName)
        statement.setString(2, student.firstName)
        statement.setString(3, student.middleName?.takeIf { it.isNotBlank() })
        statement.setString(4, student.phone?.takeIf { it.isNotBlank() })
        statement.setString(5, student.telegram?.takeIf { it.isNotBlank() })
        statement.setString(6, student.email?.takeIf { it.isNotBlank() })
        statement.setString(7, student.git)
        statement.executeUpdate()
    }

    // Заменить элемент по ID
    fun updateStudent(id: Int, student: Student) {
        val sql = "UPDATE student SET lastname = ?, firstname = ?, middlename = ?, phone = ?, telegram = ?, email = ?, git = ? WHERE id = ?"
        val statement = connection.prepareStatement(sql)
        statement.setString(1, student.lastName)
        statement.setString(2, student.firstName)
        statement.setString(3, student.middleName ?: "")
        statement.setString(4, student.phone)
        statement.setString(5, student.telegram)
        statement.setString(6, student.email)
        statement.setString(7, student.git)
        statement.setInt(8, id)
        statement.executeUpdate()
    }

    // Удалить элемент по ID
    fun deleteStudent(id: Int) {
        val sql = "DELETE FROM student WHERE id = ?"
        val statement = connection.prepareStatement(sql)
        statement.setInt(1, id)
        statement.executeUpdate()
    }

    // Получить количество элементов
    fun getTotalStudents(): Int {
        val sql = "SELECT COUNT(*) FROM student"
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(sql)

        return if (resultSet.next()) {
            resultSet.getInt(1)
        } else {
            0
        }
    }
}