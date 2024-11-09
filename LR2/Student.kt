import java.io.File
import java.io.FileWriter

class Student(
    surnameValue: String,
    nameValue: String,
    patronymicValue: String,
    idValue:Int = autoGenerateId(),
    phoneNumberValue: String?=null,
    telegramValue: String?=null,
    emailValue: String?=null,
    gitHubValue: String?=null): StudentBase(idValue,gitHubValue)
{

    var surname:String=surnameValue
        set(value:String) {
            StudentValidator.validatorSurname(value)
            field = value;
        };

    override var id:Int = idValue
        set(value:Int){
            field = value
            setMaxId(value)
        }

    var name:String=nameValue
        set(value:String) {
            StudentValidator.validatorName(value)
            field = value;
        };

    var patronymic:String=patronymicValue
        set(value:String) {
            StudentValidator.validatorPatronymic(value)
            field = value;
        };

    var phoneNumber:String?=phoneNumberValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                StudentValidator.validatorPhoneNumber(value)
                field = value;
            }
        };

    var telegram:String?=telegramValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                StudentValidator.validatorTelegram(value)
                field = value;
            }
        };

    var email:String?=emailValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                StudentValidator.validatorEmail(email)
                field = value;
            }
        };

    override var gitHub:String?=gitHubValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                StudentValidator.validatorGit(value)
                field = value;
            }
        };

    init {
        StudentValidator.validatorSurname(this.surname)
        StudentValidator.validatorName(this.name)
        StudentValidator.validatorPatronymic(this.patronymic)
        StudentValidator.validatorPhoneNumber(this.phoneNumber)
        StudentValidator.validatorTelegram(this.telegram)
        StudentValidator.validatorEmail(this.email)
        StudentValidator.validatorGit(this.gitHub);
    }

    private fun gitExist() = this.gitHub!=null

    private fun contactExist() = this.email!=null || this.telegram!=null || this.phoneNumber!=null

    fun validate() = this.gitExist() && this.contactExist()

    fun setContacts(contacts:HashMap<String,String?>){
        this.phoneNumber = contacts.getOrDefault("phoneNumber",this.phoneNumber);
        this.gitHub = contacts.getOrDefault("gitHub",this.gitHub);
        this.email = contacts.getOrDefault("email",this.email)
        this.telegram = contacts.getOrDefault("telegram",this.telegram);
    }

    private fun checkValueAndPropertyNotNull(value:String?,propertyValue:String?) = value==null && propertyValue!=null || value!=null
    companion object{
        //Парсер строки
        private fun cutStudent(data:String) = data.split("^Student\\(".toRegex())[1].split("\\)$".toRegex())[0]
        fun parseString(data:String):HashMap<String,Any?> {
            val dataWithoutPrefix = cutStudent(data)
            return StudentBase.parseString(dataWithoutPrefix)
        }
        fun readFromTxt(filePath: String):Array<Student> {
            val file = File(filePath)
            if(!file.exists()){
                throw Exception("Файл не существует!")
            }
            else{
                val lines = file.readLines().map { Student(it) }
                return lines.toTypedArray()
            }
        }
        fun writeToTxt(filePath: String,fileName:String,students:Array<Student>) {
            val file = File(filePath+"/${fileName}")
            if(!file.exists()){
                file.createNewFile()
            }
            val fileWriter = FileWriter(filePath+"/${fileName}")
            students.forEach { fileWriter.appendLine(it.toString()) }
            fileWriter.close()
        }
    }

    constructor(studentArgs: HashMap<String,Any?>) : this(
        surnameValue     = studentArgs["surname"].toString(),
        nameValue        = studentArgs["name"].toString(),
        patronymicValue  = studentArgs["patronymic"].toString(),
        idValue          = if(studentArgs.getOrDefault("id",null).toString().toIntOrNull()==null) autoGenerateId() else (studentArgs["id"].toString().toInt()),
        phoneNumberValue = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegramValue    = studentArgs.getOrDefault("telegram",null) as String?,
        emailValue       = studentArgs.getOrDefault("email",null) as String?,
        gitHubValue      = studentArgs.getOrDefault("gitHub",null) as String?)

    constructor(data: String) : this(parseString(data))
    //Для корректного отображения строки
    override fun propertiesReturn(): Map<String, Any?> =
        mapOf(
            "id" to this.id,
            "surname" to this.surname,
            "name" to this.name,
            "patronymic" to this.patronymic,
            "phoneNumber" to this.phoneNumber,
            "email" to this.email,
            "telegram" to this.telegram,
            "gitHub" to this.gitHub
        )

    override fun toString(): String = "Student".plus(super.toString())

    //Краткая информация о студенте
    fun getInitials() = "${this.surname} ${this.name[0].plus(".")} ${this.patronymic[0].plus(".")}"
    fun getOneContact(): HashMap<String,String>? =
        when{
            this.phoneNumber!=null -> hashMapOf("phoneNumber" to this.phoneNumber as String)
            this.telegram!=null -> hashMapOf("telegram" to this.telegram as String)
            this.email!=null -> hashMapOf("email" to this.email as String)
            else -> null
        }

    private fun getFormattedContactShort():String{
        val contact = getOneContact()
        return if(contact?.keys!=null)formatPropertyOutput(contact.keys.first() as String,contact.values.first()) else ""
    }

    fun getInfo():String{
        return "Initials:${this.getInitials()}, ${formatPropertyOutput("gitHub",this.gitHub)}, Contact:${getFormattedContactShort()}"
    }
}