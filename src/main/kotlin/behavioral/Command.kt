package behavioral

// Command Pattern basically provides a standard solution
// to distinguish between the objects which WANTS to do some operation
// and which WILL do the operation

// Difference between Chain of Responsibility and Command Pattern is
// CRP gives same task to all objects on in the chain.
// CP gives task only to a particular object which can perform the operation.

// Real life example of this pattern can be how ATMs work.

class ATM {
    fun clear(){
        println("ATM::CLEAR")
    }
    fun enter(){
        println("ATM::ENTER")
    }
    fun delete(){
        println("ATM::DELETE")
    }
}

// Abstract Command
interface Command {
    fun execute()
}

// Concrete Commands
class ClearScreen : Command {
    // Clear Screen -> execute

    private var atm: ATM? = null

    init {
        atm = ATM()
    }

    override fun execute() {
        atm?.clear()
    }
}
class Delete : Command {
    // Delete -> execute

    private var atm: ATM? = null

    init {
        atm = ATM()
    }

    override fun execute() {
        atm?.delete()
    }
}
class Enter : Command {
    // Enter -> execute

    private var atm: ATM? = null

    init {
        atm = ATM()
    }

    override fun execute() {
        atm?.enter()
    }
}

// Invoker
class KeyPad {
    // Invoker -> Command

    private var commands = HashMap<String, Command>()

    init {
        commands["Delete"] = Delete()
        commands["Enter"] = Enter()
        commands["Clear"] = ClearScreen()
    }

    fun runCommand(commandName: String){
        commands[commandName]?.execute()
    }
}

// TEST
fun main(){
    val kp = KeyPad()
    kp.runCommand("Enter")
    kp.runCommand("Delete")
    kp.runCommand("Clear")
}