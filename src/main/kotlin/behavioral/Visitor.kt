package behavioral

// We can use Visitor Pattern for adding new features to certain classes without
// making any changes on them and retrieving class type we lost
// while using interfaces without using "instanceof".

// Real life example of VP can be adding DLC packages to a game.

interface IVisitor {
    // Visitor interface -> Customer interface
    fun visit(c: Customer)
}

interface ICustomer {
    fun accept(v: IVisitor)
}

open class Customer(name: String, surname: String){
    var _name: String
    var _surname: String

    init {
        _name = name
        _surname = surname
    }
}

class PersonalCustomer(
    name: String,
    surname: String
) : Customer(name, surname), ICustomer {
    // Personal Customer -> Customer interface
    override fun accept(v: IVisitor) {
        v.visit(this as Customer)
    }
}

class EnterpriseCustomer(
    name: String,
    surname: String,
    company: String
) : Customer(name, surname), ICustomer {
    // Enterprise Customer -> Customer interface
    var _company: String = company

    override fun accept(v: IVisitor) {
        v.visit(this as Customer)
    }
}

class MortgageVisitor : IVisitor {
    // Mortgage Visitor -> Customer interface
    override fun visit(c: Customer) {
        if (c is PersonalCustomer) {
            println("Personal customer: ${c._name} ${c._surname} can do mortgage.")
        } else if (c is EnterpriseCustomer) {
            println("Enterprise customer: ${c._name} ${c._surname} from ${c._company} cannot do mortgage.")
        }
    }
}

class LeasingVisitor : IVisitor {
    // Leasing Visitor -> Customer interface
    override fun visit(c: Customer) {
        if (c is PersonalCustomer) {
            println("Personal customer: ${c._name} ${c._surname} cannot use leasing.")
        } else if (c is EnterpriseCustomer) {
            println("Enterprise customer: ${c._name} ${c._surname} from ${c._company} can use leasing.")
        }
    }
}

class UseCredit {
    // Use Credit -<<use>>-> Customer interface
    companion object {
        fun mortgage(c: ICustomer){
            c.accept(MortgageVisitor())
        }
        fun leasing(c: ICustomer){
            c.accept(LeasingVisitor())
        }
    }
}

// TEST
fun main(){
    val pc = PersonalCustomer("Laurence", "Barnes")
    val ec = EnterpriseCustomer("Michael", "Sykes", "Raptor inc.")

    UseCredit.leasing(pc)
    UseCredit.leasing(ec)

    UseCredit.mortgage(pc)
    UseCredit.mortgage(ec)
}