class StudentShort: StudentBase {
    val initials:String
    val contact:Map<String,String>?;

    constructor(studentLong:Student):super(studentLong.id,studentLong.gitHub) {
        this.initials = studentLong.getInitials()
        this.contact = studentLong.getOneContact()
    }

    // Конструктор по строке
    constructor(studentId:Int,stringInfo:String):super(studentId, passGit(stringInfo)){
        val stringInfoParse = StudentBase.parseString(stringInfo)
        this.initials = validateSurnameInitials(stringInfoParse.getOrDefault("Initials",null) as String?)
        this.contact = validateContact(stringInfoParse.getOrDefault("Contact",null) as String?)
        validatorContact(this.contact)
    }
    companion object{
        private fun passGit(stringInfo:String):String?{
            val data = StudentBase.parseString(stringInfo)
            val gitHub = data.getOrDefault("gitHub",null) as String?
            return if(StudentValidator.isValidGitHub(gitHub)) gitHub else null
        }

        private fun validatorContact(contact:Map<String,String>?):Boolean{
            if(contact==null) return true;
            when {
                contact.keys.first() == "phoneNumber" && StudentValidator.isValidPhone(contact.values.first()) -> return true
                contact.keys.first() == "email" && StudentValidator.isValidEmail(contact.values.first()) -> return true
                contact.keys.first() == "telegram" && StudentValidator.isValidTelegram(contact.values.first()) -> return true

            }
            return false;
        }

        private fun validatorInitials(initials:String){
            val data = initials.split(" ", limit = 2)
            StudentValidator.validatorSurname(data[0])
            val initialsSplit = data[1].split(" ")
            StudentValidator.validatorName(initialsSplit[0].dropLast(1))
            StudentValidator.validatorPatronymic(initialsSplit[1].dropLast(1))
        }
        private fun validateContact(value: String?):Map<String,String>? {
            if(value==null) return null;
            val dataSplit = value.split(":")
            when {
                dataSplit[0] == "phoneNumber" && StudentValidator.isValidPhone(dataSplit[1]) -> return mapOf("phoneNumber" to dataSplit[1])
                dataSplit[0] == "email" && StudentValidator.isValidEmail(dataSplit[1]) -> return mapOf("email" to dataSplit[1])
                dataSplit[0] == "telegram" && StudentValidator.isValidTelegram(dataSplit[1]) -> return mapOf("telegram" to dataSplit[1])
            }
            return null;
        }
        private fun validateSurnameInitials(value:String?):String{
            if(value==null) throw Exception("Неверно введено ФИО")
            return value;
        }
        fun returnPropertyNames() = StudentShort(-1,"Initials:A A.A., gitHub:, Contact:").propertiesReturn().keys
    }
    // Структурированный вывод
    override fun propertiesReturn():Map<String,Any?> =
        mapOf(
            "id" to this.id,
            "initials" to this.initials,
            "gitHub" to this.gitHub,
            "contact" to this.contact)
    override fun toString(): String = "StudentShort".plus(super.toString())
}