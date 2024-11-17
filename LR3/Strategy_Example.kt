interface Strategy {
    fun execute(a: Int, b: Int): Int
}

class AddStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int {
        return a + b
    }
}

class SubtractStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int {
        return a - b
    }
}

class Calculator(private var strategy: Strategy) {
    fun setStrategy(strategy: Strategy) {
        this.strategy = strategy
    }
    fun executeOperation(a: Int, b: Int): Int {
        return strategy.execute(a, b)
    }
}

fun main() {
    val calculator = Calculator(AddStrategy())
    println("10 + 5 = ${calculator.executeOperation(10, 5)}")

    // Меняем стратегию на вычитание
    calculator.setStrategy(SubtractStrategy())
    println("10 - 5 = ${calculator.executeOperation(10, 5)}")
}
