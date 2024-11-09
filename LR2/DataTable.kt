class DataTable(private val data: Array<Array<Any?>>) {
    fun getElement(row: Int, col: Int):Any? {
        return if (row in data.indices && col in data[row].indices) {
            data[row][col].copyIfPossible()
        } else {
            null // Если индексы некорректны - возвращаем null
        }
    }
    fun getColumnCount(): Int {
        return data[0].size
    }
    fun getRowCount(): Int {
        return data.size
    }
}
// Функция для создания копии элемента (если это возможно)
fun <T> T.copyIfPossible(): T {
    return when (this) {
        is Cloneable -> this!!::class.java.getMethod("clone").invoke(this) as T
        else -> this
    }
}