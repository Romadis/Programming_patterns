import kotlin.math.max

abstract class StudentBase(open val id:Int = autoGenerateId(), open val gitHub: String?=null) {
    abstract fun propertiesReturn():Map<String,Any?>
    init {
        StudentValidator.validatorGit(this.gitHub)
    }
    companion object {
        private var classId:Int = 0
        private var maxId:Int = 0;
        internal fun autoGenerateId():Int{
            classId= maxId
            maxId = maxId + 1;
            return classId
        }
        internal fun setMaxId(newId:Int?){
            maxId = max(maxId,newId as Int);
        }
        internal fun formatPropertyOutput(propertyName:String,propertyValue: Any?) = if(propertyValue==null) "${propertyName}:" else "${propertyName}:${propertyValue}"
        fun parseString(data:String):HashMap<String,Any?>{
            val dataSplit = data.split(',')
            val hashData = HashMap<String,Any?>();
            for (propertyValue in dataSplit){
                if(!propertyValue.trim().matches("[a-zA-Z]+:.*".toRegex())){
                    throw Exception("Неверный формат строки")
                }
                else{
                    val (key,propertyVal) = propertyValue.split(":".toRegex(),2);
                    hashData.set(key.trim(),if(propertyVal=="") null else propertyVal.trim());
                }
            }
            return hashData
        }
    }

    override fun toString(): String {
        var resultString = "("
        for ((key,propValues) in propertiesReturn().entries){
            resultString += "${formatPropertyOutput(key,propValues)},"
        }
        return resultString.dropLast(1).plus(")")
    }
}