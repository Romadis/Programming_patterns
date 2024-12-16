interface Target {
    fun request(): String
}

class Adaptee {
    fun specificRequest(): String {
        return "Specific request from Adaptee"
    }
}

class Adapter(private val adaptee: Adaptee) : Target {
    override fun request(): String {
        // Адаптация вызова
        return adaptee.specificRequest()
    }
}

fun main() {
    val adaptee = Adaptee()
    val adapter: Target = Adapter(adaptee)
    println(adapter.request()) // Specific request from Adaptee
}
