data class Student(
    private var surname:String,
    private var name:String,
    private var patronymic:String,
    private var id:Int= autoGenerateId(),
    private var phoneNumber:String?=null,
    private var telegram:String?=null,
    private var email:String?=null,
    private var gitHub:String?=null
)
{
    init {
        require(
            isValidSurname(this.surname),
            { "Фамилия должна быть действительной" }
        )
        require(
            isValidName(this.name),
            { "Имя должно быть действительным" }
        )
        require(
            isValidPatronymic(this.patronymic),
            { "Отчество должно быть действительным" }
        )
        require(
            isValidPhone(this.phoneNumber),
            { "Номер телефона должен быть действительным" }
        )
        require(
            isValidTelegram(this.telegram),
            { "Телеграм должен быть действительным" }
        )
        require(
            isValidEmail(this.email),
            { "Адрес электронной почты должен быть действительным" }
        )
        require(
            isValidGitHub(this.gitHub),
            { "Git должен быть действительным" }
        )
    }

    private fun gitExist() = this.gitHub!=null

    private fun contactExist() = this.email!=null || this.telegram!=null || this.phoneNumber!=null

    fun validate() = this.gitExist() && this.contactExist()

    //Метод set_contacts
    private fun checkValueAndPropertyNotNull(value:String?,propertyValue:String?) = value==null && propertyValue!=null || value!=null
    private fun contactSetter(value: String?,propertyValue: String?,setter:(value: String?)->(Unit)){
        if(this.checkValueAndPropertyNotNull(value,propertyValue)){
            setter(value)
        }
    }

    private fun phoneSetter(value: String?) {
        require(isValidPhone(value))
        this.phoneNumber = value
    }
    private fun setPhoneNumber(value:String?) = this.contactSetter(value, this.phoneNumber) { this.phoneSetter(it) }

    private fun telegramSetter(value: String?) {
        require(isValidTelegram(value))
        this.telegram = value
    }
    private fun setTelegram(value:String?) = this.contactSetter(value,this.telegram,this::telegramSetter)

    private fun emailSetter(value: String?) {
        require(isValidEmail(value))
        this.email = value
    }
    private fun setEmail(value:String?) = this.contactSetter(value,this.email,this::emailSetter)

    private fun gitSetter(value: String?) {
        require(isValidGitHub(value))
        this.gitHub = value
    }
    private fun setGit(value:String?) = this.contactSetter(value,this.gitHub,this::gitSetter);

    fun setContacts(contacts:HashMap<String,String?>){
        this.setPhoneNumber(contacts.getOrDefault("phoneNumber",this.phoneNumber))
        this.setGit(contacts.getOrDefault("gitHub",this.gitHub))
        this.setEmail(contacts.getOrDefault("email",this.email))
        this.setTelegram(contacts.getOrDefault("telegram",this.telegram))
    }

    companion object{
        var classId:Int = 0
        fun autoGenerateId():Int{
            classId+=1
            return classId
        }
        fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true
        }

        fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"))
        }

        fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"))
        }

        fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"))
        }

        fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true
        }

        fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true
        }

        fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true
        }
    }
    constructor(studentArgs: HashMap<String,Any?>) : this(
        surname = studentArgs["surname"].toString(),
        name = studentArgs["name"].toString(),
        patronymic = studentArgs["patronymic"].toString(),
        phoneNumber = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegram = studentArgs.getOrDefault("telegram",null) as String?,
        email = studentArgs.getOrDefault("email",null) as String?,
        gitHub = studentArgs.getOrDefault("gitHub",null) as String?)
}
