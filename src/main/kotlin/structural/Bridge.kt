package structural

// Bridge Pattern separates an abstraction into two.
// "Bridge" is the connector between implementation and abstract class.

// Real life BP how a single charging brick can charge many type of devices.

abstract class ICharger {
    abstract fun charger(device: String, speed: Int)
}

class ChargePhone : ICharger() {
    override fun charger(device: String, speed: Int) {
        println("Charging $device at ${speed}W.")
    }
}

class ChargeTablet : ICharger() {
    override fun charger(device: String, speed: Int) {
        println("Charging $device at ${speed}W.")
    }
}

class ChargeHeadphones : ICharger() {
    override fun charger(device: String, speed: Int) {
        println("Charging $device at ${speed}W.")
    }
}

abstract class Device {
    protected var c: ICharger? = null

    fun setCharger(ch: ICharger){
        c = ch
    }

    open fun charge(device: String, speed: Int){
        c?.charger(device, speed)
    }
}

class MyDevice : Device() {
    override fun charge(device: String, speed: Int) {
        c?.charger(device, speed)
    }
}

fun main(){
    val d = MyDevice()

    d.setCharger(ChargePhone())
    d.charge("Phone" , 30)

    d.setCharger(ChargeTablet())
    d.charge("Tablet" , 15)

    d.setCharger(ChargeHeadphones())
    d.charge("Headphones" , 5)
}