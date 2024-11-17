package main.src
import java.io.File
import java.lang.Exception

open class SuperStudent(
    var id: Int,
    var lastName: String,
    var firstName: String,
    var middleName: String,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var git: String? = null
) {
    init {
        if (!validate()) {
            throw IllegalArgumentException("У студента должен быть хотя-бы один контакт или гит.")
        }
    }

    // Проверка наличия гита
    fun hasGit(): Boolean {
        return git?.isNotBlank() == true
    }

    // Проверка наличия контакта (хотя бы одного)
    fun hasContact(): Boolean {
        return !phone.isNullOrBlank() || !telegram.isNullOrBlank() || !email.isNullOrBlank()
    }

    fun validate() : Boolean {
        return hasGit() && hasContact()
    }

    //Новый метод getInfo
    fun getInfo(): String {
        val initials = "${firstName.first()}.${middleName.firstOrNull()?.toString() ?: ""}."
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
}