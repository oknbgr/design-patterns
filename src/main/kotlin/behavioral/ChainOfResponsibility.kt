package behavioral

// Chain of Responsibility Pattern utilizes concept of Client's sending request to object(s)
// which it does not have any information about beforehand
// to get something done

// The best real life example of this pattern is how we use compilers in computers

// Handler Object
class Operator(name: String){
    private var name: String = ""
    var isAvailable: Boolean = false
    var nextOperator: Operator? = null

    init {
        this.name = name
    }

    fun answerCall(){
        val n = nextOperator

        if (isAvailable){
            // Answer
            println("$name answered call.")
        } else if (n != null) {
            // Forward to next
            n.answerCall()
        } else {
            println("Call is on hold...")
        }
    }
}

class CallCenter {
    // Call Center -- Operator

    private val agents = ArrayList<Operator>()

    init {
        val o1 = Operator("Trevor")
        val o2 = Operator("Michael")
        val o3 = Operator("Franklin")
        val o4 = Operator("Jimmy")

        o1.isAvailable = false
        o2.isAvailable = false
        o3.isAvailable = true // Franklin is available
        o4.isAvailable = false

        o1.nextOperator = o2
        o2.nextOperator = o3
        o3.nextOperator = o4
        o4.nextOperator = null

        agents.add(o1)
        agents.add(o2)
        agents.add(o3)
        agents.add(o4)
    }

    fun receiveCall() {
        // Give task to first operator
        agents[0].answerCall()
    }
}

class Client {
    // Client -<<use>>-> Call Center
    companion object {
        fun openCall(cc: CallCenter) {
            cc.receiveCall()
        }
    }
}

// TEST
fun main(){
    // Clients know operators
    // Operators know clients
    Client.openCall(CallCenter())
}