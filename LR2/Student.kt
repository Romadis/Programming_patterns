import kotlin.math.max

class Student(
    surnameValue: String,
    nameValue: String,
    patronymicValue: String,
    idValue:Int? = null,
    phoneNumberValue: String?=null,
    telegramValue: String?=null,
    emailValue: String?=null,
    gitHubValue: String?=null)
{
    var surname:String=surnameValue
        set(value:String) {
            validatorSurname(value)
            field = value;
        };
    var name:String=nameValue
        set(value:String) {
            validatorName(value)
            field = value;
        };
    var patronymic:String=patronymicValue
        set(value:String) {
            validatorPatronymic(value)
            field = value;
        };
    var phoneNumber:String?=phoneNumberValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                validatorPhoneNumber(value)
                field = value;
            }
        };
    var telegram:String?=telegramValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                validatorTelegram(value)
                field = value;
            }
        };
    var email:String?=emailValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                validatorEmail(email)
                field = value;
            }
        };
    var gitHub:String?=gitHubValue
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                validatorGit(value)
                field = value;
            }
        };
    var id:Int? = idValue
        set(value:Int?){
            if(!isValidId(value)){
                field = autoGenerateId()
            }
            else{
                field = value
            }
            setMaxId(value)
        }
    init {
        if(!isValidId(this.id)){
            this.id = autoGenerateId()
        }
        validatorSurname(this.surname)
        validatorName(this.name)
        validatorPatronymic(this.patronymic)
        validatorPhoneNumber(this.phoneNumber)
        validatorTelegram(this.telegram)
        validatorEmail(this.email)
        validatorGit(this.gitHub);
    }

    // Валидация полей по регулярному выражению
    private fun <T>validatorFunc(value:T, errorMessage:String, valudatorFunction: (T)->Boolean){
        require(valudatorFunction(value)) { errorMessage }
    }

    private fun validatorGit(gitHub: String?) = validatorFunc(gitHub,"Git must be a valid git",::isValidGitHub)

    private fun validatorEmail(email: String?) = validatorFunc(email,"Email must be a valid email",::isValidEmail)

    private fun validatorPhoneNumber(phone: String?) = validatorFunc(phone, "Phone must be a valid phone number",::isValidPhone)

    private fun validatorTelegram(telegram: String?) = validatorFunc(telegram, "Telegram must be a valid telegram",::isValidTelegram)

    private fun validatorSurname(surname: String) = validatorFunc(surname, "Surname must be a valid surname",::isValidSurname)

    private fun validatorName(name: String) = validatorFunc(name, "Name must be a valid name",::isValidName)

    private fun validatorPatronymic(patronymic: String) = validatorFunc(patronymic, "Patronymic must be a valid patronymic",::isValidPatronymic)

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
        // Автоматическая генерация id
        private var classId:Int = 0
        private var maxId:Int = 0;
        private fun autoGenerateId():Int{
            classId= maxId
            maxId = maxId + 1;
            return classId
        }
        private fun setMaxId(newId:Int?){
            maxId = max(maxId,newId as Int);
        }

        private fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true
        }

        private  fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"))
        }

        private  fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"))
        }

        private  fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"))
        }

        private  fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true
        }

        private  fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true
        }

        private  fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true
        }

        private fun isValidId(id: Int?) = if(id==null) false else true;

        private fun cutStudent(data:String) = data.split("^Student\\(".toRegex())[1].split("\\)$".toRegex())[0]
        fun parseString(data:String):HashMap<String,Any?>{
            val dataWithoutPrefix = cutStudent(data).split(',')
            val hashData = HashMap<String,Any?>();
            for (propertyValue in dataWithoutPrefix){
                if(!propertyValue.matches("[a-zA-Z]{2,11}:.*".toRegex())){
                    throw Exception("Неверный формат строки")
                }
                else{
                    val (key,propertyVal) = propertyValue.split(":".toRegex());
                    hashData.set(key,if(propertyVal=="") null else propertyVal);
                }
            }
            return hashData
        }
        private fun formatPropertyOutput(propertyName:String,propertyValue: Any?) = if(propertyValue==null) "${propertyName}:" else "${propertyName}:${propertyValue}"
    }

    constructor(studentArgs: HashMap<String,Any?>) : this(
        surnameValue     = studentArgs["surname"].toString(),
        nameValue        = studentArgs["name"].toString(),
        patronymicValue  = studentArgs["patronymic"].toString(),
        idValue          = studentArgs.getOrDefault("id",null).toString().toIntOrNull(),
        phoneNumberValue = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegramValue    = studentArgs.getOrDefault("telegram",null) as String?,
        emailValue       = studentArgs.getOrDefault("email",null) as String?,
        gitHubValue      = studentArgs.getOrDefault("gitHub",null) as String?)

    constructor(data: String) : this(parseString(data))
    private fun propertiesReturn() =
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

    override fun toString(): String {
        var resultString = "Student("
        for ((key,propValues) in propertiesReturn().entries){
            resultString += "${formatPropertyOutput(key,propValues)},"
        }
        return resultString.dropLast(1).plus(")")
    }

    //Краткая информация о студенте
    private fun getInitials() = "${this.surname} ${this.name[0].plus(".")} ${this.patronymic[0].plus(".")}"
    private fun getOneContact() =
        when{
            this.phoneNumber!=null -> hashMapOf("phoneNumber" to this.phoneNumber)
            this.telegram!=null -> hashMapOf("telegram" to this.telegram)
            this.email!=null -> hashMapOf("email" to this.email)
            else -> null
        }
    fun getInfo():String{
        val contact = getOneContact()
        return "Initials:${this.getInitials()} Contact: ${contact?.keys?.first()}:${contact?.values?.first()}"
    }
}