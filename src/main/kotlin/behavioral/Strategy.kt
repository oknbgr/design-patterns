package behavioral

// Strategy Pattern is implemented by using temporary tools
// and sticking to a single solution way among multiple solutions
// for solving a problem.

// Real life example of SP can be how we transport in the city,
// We can take the bus, metro or taxi. There are multiple solutions
// for one problem.

// Abstract Strategy
interface Education {
    fun learn()
}

// Concrete Strategy Classes
class SelfTaught : Education {
    // Self Taught -> Education
    override fun learn() {
        println("Learnt from self taught.")
    }
}

class University : Education {
    // University -> Education
    override fun learn() {
        println("Learnt from university.")
    }
}

class Bootcamp : Education {
    // Bootcamp -> Education
    override fun learn() {
        println("Learnt from bootcamp.")
    }
}

// Context
class MobileDev(e: Education){
    // Mobile Developer -> Education
    private var e: Education

    init {
        this.e = e
    }

    fun become(){
        e.learn()
        println("Mobile Developer")
    }
}

class WebDev(e: Education){
    // Web Developer -> Education
    private var e: Education

    init {
        this.e = e
    }

    fun become(){
        e.learn()
        println("Web Developer")
    }
}

// TEST
fun main(){
    val e1 = SelfTaught()
    val e2 = University()
    val e3 = Bootcamp()

    val md1 = MobileDev(e1)
    md1.become()

    println("----------------------------------")

    val md2 = MobileDev(e2)
    md2.become()

    println("----------------------------------")

    val wd = WebDev(e3)
    wd.become()
}