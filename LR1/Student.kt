data class Student(
    var surname:String,
    var name:String,
    var patronymic:String,
    var id:Int= autoGenerateId(),
    var phoneNumber:String?=null,
    var telegram:String?=null,
    var email:String?=null,
    var gitHub:String?=null
)
{
    init {
        require(
            Student.isValidSurname(this.surname),
            { "Фамилия должна быть действительной" }
        );
        require(
            Student.isValidName(this.name),
            { "Имя должно быть действительным" }
        );
        require(
            Student.isValidPatronymic(this.patronymic),
            { "Отчество должно быть действительным" }
        );
        require(
            Student.isValidPhone(this.phoneNumber),
            { "Номер телефона должен быть действительным" }
        );
        require(
            Student.isValidTelegram(this.telegram),
            { "Телеграм должен быть действительным" }
        );
        require(
            Student.isValidEmail(this.email),
            { "Адрес электронной почты должен быть действительным" }
        );
        require(
            Student.isValidGitHub(this.gitHub),
            { "Git должен быть действительным" }
        );
    }
    fun gitExist() = this.gitHub!=null

    fun contactExist() = this.email!=null || this.telegram!=null || this.phoneNumber!=null;

    fun validate() = this.gitExist() && this.contactExist();

    companion object{
        var classId:Int = 0;
        fun autoGenerateId():Int{
            classId+=1;
            return classId;
        }
        fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true;
        }

        fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"));
        }

        fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"));
        }

        fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[А-Я][а-я]*(-([А-Яа-я]?)[а-я]*)*$"));
        }

        fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true;
        }

        fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true;
        }

        fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true;
        }
    }
    constructor(studentArgs: HashMap<String,Any?>) : this(id = studentArgs.getOrDefault("id", autoGenerateId()) as Int,
        surname = studentArgs["surname"].toString(),
        name = studentArgs["name"].toString(),
        patronymic = studentArgs["patronymic"].toString(),
        phoneNumber = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegram = studentArgs.getOrDefault("telegram",null) as String?,
        email = studentArgs.getOrDefault("email",null) as String?,
        gitHub = studentArgs.getOrDefault("gitHub",null) as String?) {}
}