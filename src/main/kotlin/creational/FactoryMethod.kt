package creational

// Factory Method Pattern is used create objects while hiding the process of how they are created.
// Like how we use money in real life, we use it regularly but don't know how to create them on our own.

abstract class Money protected constructor(
    private val mNominalValue: Int
){
    // Initializer function is always hidden (abstract).
    // Therefore, user unable call it directly,
    // But override it.
    fun getNominalValue(): String {
        return mNominalValue.toString() + "USD"
    }
}

// Creator Factory Method is often inherits from a different object dependent class.
// However, they can be a member of initial object rarely.
class USDollar constructor(
    nominalValue: Int
) : Money(nominalValue) {
    // Money -> USDollar
}

abstract class Bank {
    abstract fun printMoney(nominalValue: Int) : Money
}

class USCentralBank : Bank() {
    // USCentralBank -> Bank
    override fun printMoney(nominalValue: Int): Money {
        // USCentralBank -<<initializes>>-> Money -> USDollar
        return USDollar(nominalValue)
    }
}

// Note: Sometimes this pattern is only used for readability.
// TEST
object FactoryMethodTest {
    @JvmStatic
    fun main(args: Array<String>){
        val bank = USCentralBank()
        val usd = bank.printMoney(1000) as USDollar

        println(usd.getNominalValue())
    }
}