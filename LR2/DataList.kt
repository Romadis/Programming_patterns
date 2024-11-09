import kotlin.reflect.full.memberProperties

open class DataList<T>(val elements: Array<T>) {
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
    open fun functionGetPropsNames() = if(this.elements.isEmpty()) arrayOf() else this.elements[0]!!::class.memberProperties.map{it.name}.toTypedArray<String>()
    fun getNames(): Array<String> = functionGetPropsNames()

    // Получение DataTable
    open fun getPropertiesOfClass(value:T):List<Any?> = arrayOf("12",2).toList()
    fun getData(): DataTable {
        val dataList:MutableList<Array<Any?>> = mutableListOf()
        var rowNumber = 0;
        for(el in this.elements){
            dataList.add(
                arrayOf(rowNumber,*getPropertiesOfClass(el).toTypedArray<Any?>())
            )
            rowNumber+=1;
        }
        return DataTable(dataList.toTypedArray<Array<Any?>>())
    }
}