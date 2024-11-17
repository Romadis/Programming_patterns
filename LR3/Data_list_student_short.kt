import main.src.Student
import main.src.SuperStudent
import main.src.Student_short

class Data_list_student_short(students: List<Student_short>) : Data_list<Student_short>(students) {
    override fun get_names(): List<String> {
        return listOf("№","Инициалы","Гит","Контакт")
    }

    // Получаем полный список
    override fun get_data(): List<List<Any>> {
        val dataList = mutableListOf<List<Any>>()
        dataList.add(get_names())
        var index =0
        for (student in data) {
            index += 1;
            val row = listOf(
                index,
                student.FIO(),
                student.git,
                student.contactMethod()
            )
            dataList.add(listOf(row))
        }
        return dataList
    }
}