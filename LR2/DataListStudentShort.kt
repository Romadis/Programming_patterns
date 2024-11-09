class DataListStudentShort(studentShortArray: Array<StudentShort>):DataList<StudentShort>(studentShortArray) {
    override fun getNames(): Array<String> = StudentShort.returnPropertyNames().toTypedArray<String>()
    override fun getData(): DataTable {
        val dataList:MutableList<Array<Any?>> = mutableListOf()
        var rowNumber = 0;
        for(student in this.elements){
            dataList.add(
                arrayOf(rowNumber,*(student as StudentShort).propertiesReturn().values.drop(1).toTypedArray())
            )
            rowNumber+=1;
        }
        return DataTable(dataList.toTypedArray<Array<Any?>>())
    }
}
