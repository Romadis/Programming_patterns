import main.src.Student_short

open class Data_list<T>(val data: List<T> = mutableListOf()) {
    private val selectedIndices = mutableSetOf<Int>()
    // Выделение элемента по номеру
    fun select(number: Int) {
        if (number in data.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Недопустимый индекс: $number")
        }
    }

    // Получение идентификаторов элементов
    fun get_selected(): List<Int> {
        return selectedIndices.toList()
    }

    // Получение значений атрибутов объекта
    open fun get_names(): List<String> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }

    // Получение данных
    open fun get_data(): List<List<Any>> {
        throw IllegalArgumentException("Данный метод необходимо реализовать в классе наследнике")
    }
}