package behavioral

// Memento Pattern is for ensures us to switch between
// various versions of an object when needed.

// Real life example of MP can be changing back to
// default settings of a device after formatting.

enum class HardwareTypes {
    // Hardware Types -- Hardware Info
    CPU,
    RAM,
    GPU,
    SSD,
    HDD,
}

class Location(x: Int, y: Int){
    var x: Int
    var y: Int

    init {
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "X: $x - Y: $y"
    }
}

class HardwareInfo {
    // Hardware Info -- Hardware Types
    // Hardware Info -- Memento
    // Hardware Info -- Hardware
    var type: HardwareTypes
    var detail: String
    var location: Location

    init {
        type = HardwareTypes.CPU
        detail = ""
        location = Location(0, 0)
    }
}

// Originator: Stores state of the object
class Hardware(hi: HardwareInfo) {
    // Hardware -- Hardware Info
    // Hardware --> Memento

    // State
    private var _hi: HardwareInfo
    var hi: HardwareInfo

    set(value) {
        _hi = value
    }
    get() {
        return _hi
    }

    init {
        this._hi = hi
    }

    fun createMemento() : Memento {
        return Memento(_hi)
    }


    // Caretaker: Gets previous state back
    fun setMemento(m: Memento){
        this.hi = m._hi
    }

    override fun toString(): String {
        return "HARDWARE INFO\n\r" +
                "Type: ${_hi.type}\n\r" +
                "Detail: ${_hi.detail}\n\r" +
                "Location: ${_hi.location.x}, ${_hi.location.y}"
    }
}

// Memento: Stores attributes of actual object
class Memento(hi: HardwareInfo) {
    // Memento -- Hardware Info

    // State
    private var hi: HardwareInfo
    var _hi: HardwareInfo

        set(value) {
            hi = value
        }
        get() {
            return hi
        }

    init {
        this.hi = hi
    }
}

// TEST
fun main(){
    val h1 = HardwareInfo()
    h1.type = HardwareTypes.GPU
    h1.detail = "RTX 3070"
    h1.location = Location(45, 35)

    val originator = Hardware(h1)
    println(originator)

    val memento: Memento = originator.createMemento()

    println("-------------------------------")

    val h2 = HardwareInfo()
    h2.type = HardwareTypes.CPU
    h2.detail = "Intel Core i7"
    h2.location = Location(12, 22)

    originator.hi = h2
    println(originator)

    println("-------------------------------")

    originator.setMemento(memento)
    println(originator)
}