import kotlin.reflect.KFunction1

class Student(
    surnameValue: String,
    nameValue: String,
    patronymicValue: String,
    phoneNumberValue: String?=null,
    telegramValue: String?=null,
    emailValue: String?=null,
    gitHubValue: String?=null
)
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
    var id:Int= autoGenerateId();
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
    init {
        validatorSurname(this.surname)
        validatorName(this.name)
        validatorPatronymic(this.patronymic)
        validatorPhoneNumber(this.phoneNumber)
        validatorTelegram(this.telegram)
        validatorEmail(this.email)
        validatorGit(this.gitHub);
    }

    private fun <T>validatorFunc(value:T, errorMessage:String, valudatorFunction: KFunction1<T, Boolean>){
        require(valudatorFunction(value)) { errorMessage }
    }
    private fun validatorGit(gitHub: String?) = validatorFunc(gitHub,"Git должен быть действительным",::isValidGitHub)

    private fun validatorEmail(email: String?) = validatorFunc(email,"Почта должна быть действительной",::isValidEmail)

    private fun validatorPhoneNumber(phone: String?) = validatorFunc(phone, "Номер телефона должен быть действительным",::isValidPhone)

    private fun validatorTelegram(telegram: String?) = validatorFunc(telegram, "Телеграм должен быть действительным",::isValidTelegram)

    private fun validatorSurname(surname: String) = validatorFunc(surname, "Фамилия должна быть действительной",::isValidSurname)

    private fun validatorName(name: String) = validatorFunc(name, "Имя должно быть действительным",::isValidName)

    private fun validatorPatronymic(patronymic: String) = validatorFunc(patronymic, "Отчество должно быть действительным",::isValidPatronymic)

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
        private var classId:Int = 0
        private fun autoGenerateId():Int{
            classId+=1
            return classId
        }
        private fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true
        }

        private fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"))
        }

        private fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"))
        }

        private fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"))
        }

        private fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true
        }

        private fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true
        }

        private fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true
        }
    }

    constructor(studentArgs: HashMap<String,Any?>) : this(
        surnameValue = studentArgs["surname"].toString(),
        nameValue = studentArgs["name"].toString(),
        patronymicValue = studentArgs["patronymic"].toString(),
        phoneNumberValue = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegramValue = studentArgs.getOrDefault("telegram",null) as String?,
        emailValue = studentArgs.getOrDefault("email",null) as String?,
        gitHubValue = studentArgs.getOrDefault("gitHub",null) as String?)

    override fun toString(): String {
        return "Student(id:${this.id},surname:${this.surname},name:${this.name},patronymic:${this.patronymic},phoneNumber:${this.phoneNumber},email:${this.email},telegram:${this.telegram},gitHub:${this.gitHub}"
    }
}
