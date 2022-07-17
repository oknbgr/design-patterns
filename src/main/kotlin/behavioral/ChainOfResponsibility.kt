package behavioral

// Chain of Responsibility Pattern utilizes concept of Client's sending request to object(s)
// which it does not have any information about beforehand
// to get something done

// The best real life example of this pattern is how we use compilers in computers

// Handler Object
class Representative(name: String){
    private var name: String = ""
    var isAvailable: Boolean = false
    var nextRepresentative: Representative? = null

    init {
        this.name = name
    }

    fun answerCall(){
        val n = nextRepresentative

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

class CustomerService {
    // Customer Service -- Representative

    private val agents = ArrayList<Representative>()

    init {
        val r1 = Representative("Trevor")
        val r2 = Representative("Michael")
        val r3 = Representative("Franklin")
        val r4 = Representative("Jimmy")

        r1.isAvailable = false
        r2.isAvailable = true // Michael is available
        r3.isAvailable = false
        r4.isAvailable = false

        r1.nextRepresentative = r2
        r2.nextRepresentative = r3
        r3.nextRepresentative = r4
        r4.nextRepresentative = null

        agents.add(r1)
        agents.add(r2)
        agents.add(r3)
        agents.add(r4)
    }

    fun receiveCall() {
        // Give task to first representative
        agents[0].answerCall()
    }
}

class Client {
    // Client -<<use>>-> Customer Service
    companion object {
        fun openCall(cs: CustomerService) {
            cs.receiveCall()
        }
    }
}

// TEST
fun main(){
    Client.openCall(CustomerService())
}