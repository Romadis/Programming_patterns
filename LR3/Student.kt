package main.src
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    var id: Int,
    val lastName: String,
    val firstName: String,
    var middleName: String? = null,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var git: String? = null
) {
    fun set_contacts(phone: String? = null, telegram: String? = null, email: String? = null, git: String? = null) {
        if (phone != null) {
            if (isValidPhone(phone)) {
                this.phone = phone
            } else {
                throw IllegalArgumentException("Неправильный формат номера телефона")
            }
        }
        if (telegram != null) {
            if (isValidTelegram(telegram)) {
                this.telegram = telegram
            } else {
                throw IllegalArgumentException("Неверный формат телеграма")
            }
        }
        if (email != null) {
            if (isValidEmail(email)) {
                this.email = email
            } else {
                throw IllegalArgumentException("Неверный формат почты")
            }
        }
        if (git != null) {
            if (isValidGit(git)) {
                this.git = git
            } else {
                throw IllegalArgumentException("Неверный формат гита")
            }
        }
    }

    // Новый конструктор, принимающий строку
    constructor(studentString: String) : this(
        id         = studentString.split(",")[0].toInt(),
        lastName   = studentString.split(",")[1],
        firstName  = studentString.split(",")[2],
        middleName = studentString.split(",").getOrNull(3).toString(),
        phone      = studentString.split(",").getOrNull(4),
        telegram   = studentString.split(",").getOrNull(5),
        email      = studentString.split(",").getOrNull(6),
        git        = studentString.split(",").getOrNull(7),
    )

    fun getInfo(): String {
        val initials = "${firstName.first()}.${middleName?.firstOrNull()?.toString() ?: ""}."
        val FIO = "$lastName $initials".trim()
        val contactMethod = when {
            telegram != null -> "Telegram $telegram"
            email != null -> "Email $email"
            phone != null -> "Phone $phone"
            else -> "Нет доступных средств связи"
        }
        return "Инициаллы:$FIO; Git:$git; Контакт:$contactMethod"
    }

    override fun toString(): String {
        return "Student(id=$id, fullName='${getInfo()}')"
    }

    companion object Examination{
        var students = mutableListOf<Student>()
        fun isValidPhone(phone: String): Boolean {
            return phone.matches(Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{10}$"))
        }
        fun isValidTelegram(telegram: String): Boolean {
            return telegram.matches(Regex("^@[0-9a-zA-Z]{3,}$"))
        }
        fun isValidEmail(email: String): Boolean {
            return email.matches(Regex("^([a-zA-Z0-9_-]{3,}@[a-zA-Z]+\\.[a-zA-Z]+)$"))
        }
        fun isValidGit(git: String): Boolean {
            return git.matches(Regex("^https://github\\.com/[a-zA-Z0-9_-]+/?\$"))
        }
        fun isValidFirstName(firstName: String): Boolean {
            return firstName.matches(Regex("^[А-ЯA-ZЁ]{1}[а-яa-zё]{2,}$"))
        }
        fun isValidLastName(lastName: String): Boolean {
            return lastName.matches(Regex("^[А-ЯA-ZЁ]{1}[а-яa-zё]{2,}$"))
        }
        fun isValidMiddleName(middleName: String): Boolean {
            return middleName.matches(Regex("^[А-ЯA-ZЁ]{1}[а-яa-zё]{2,}$"))
        }

        // Метод, получающий писок студентов
        fun getStudent(): List<Student> {
            return students.toList()
        }
    }
}