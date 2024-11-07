class StudentValidator {
    companion object {

        internal fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$"))
        }

        internal fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$"))
        }

        internal fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$"))
        }

        internal fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true
        }

        internal   fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true
        }

        internal   fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true
        }

        internal   fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true
        }

        //Валидации полей по регулярному выражению
        internal fun <T>validatorFunc(value:T, errorMessage:String, valudatorFunction: (T)->Boolean){
            require(valudatorFunction(value)) { errorMessage }
        }

        internal fun validatorSurname(surname: String) = validatorFunc(surname, "Surname must be a valid surname",::isValidSurname)

        internal fun validatorName(name: String) = validatorFunc(name, "Name must be a valid name",::isValidName)

        internal fun validatorPatronymic(patronymic: String) = validatorFunc(patronymic, "Patronymic must be a valid patronymic",::isValidPatronymic)

        internal fun validatorGit(gitHub: String?) = validatorFunc(gitHub,"Git must be a valid git",::isValidGitHub)

        internal fun validatorEmail(email: String?) = validatorFunc(email,"Email must be a valid email",::isValidEmail)

        internal fun validatorPhoneNumber(phone: String?) = validatorFunc(phone, "Phone must be a valid phone number",::isValidPhone)

        internal fun validatorTelegram(telegram: String?) = validatorFunc(telegram, "Telegram must be a valid telegram",::isValidTelegram)
    }
}