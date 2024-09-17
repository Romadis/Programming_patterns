data class Student(
    var surname:String,
    var name:String,
    var patronymic:String,
    var id:Int?= autoGenerateId(),
    var phoneNumber:String?=null,
    var telegram:String?=null,
    var email:String?=null,
    var gitHub:String?=null
)
{
    companion object{
        var classId:Int = 0;
        fun autoGenerateId():Int{
            classId+=1;
            return classId;
        }
    }
    constructor(studentArgs: HashMap<String,Any?>) : this(id = studentArgs.getOrDefault("id", autoGenerateId()) as Int?,
        surname = studentArgs["surname"].toString(),
        name = studentArgs["name"].toString(),
        patronymic = studentArgs["patronymic"].toString(),
        phoneNumber = studentArgs.getOrDefault("phoneNumber",null).toString(),
        telegram = studentArgs.getOrDefault("telegram",null).toString(),
        email = studentArgs.getOrDefault("email",null).toString(),
        gitHub = studentArgs.getOrDefault("gitHub",null).toString()){}
}