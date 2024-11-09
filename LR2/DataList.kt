abstract class DataList<T>(val elements: Array<T>) {
    private val selectedIndices = mutableSetOf<Int>()

    // Выделение элемента по номеру
    fun select(number: Int) {
        if (number in elements.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Индекс вне диапазона")
        }
    }

    // Получение массива ID выделенных элементов
    fun getSelectedIds() = selectedIndices.toIntArray()

    // Получение массива наименований атрибутов
    abstract fun getNames(): Array<String>

    // Получение DataTable
    abstract fun getData(): DataTable
}