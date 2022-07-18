package behavioral

// State Pattern is for tracking meaningful changes in object's data
// Which cause behavioral changes in object itself.
// By that way, object can behave like it changed its class.

// Real life example of SP can be how we put our phones in silent, vibrate, sound modes.

interface ICarState {
    fun gas(context: CarContext)
    fun brake(context: CarContext)
    fun turn(context: CarContext)
}

class CarContext {
    // Car Context -> Car State interface
    var state: ICarState

    init {
        println("Started car's engine...")
        state = StoppedState()
    }

    fun accelerate(){
        state.gas(this)
    }

    fun decelerate(){
        state.brake(this)
    }

    fun steer(){
        state.turn(this)
    }
}

class GoingState : ICarState {
    // Going State -> Car State interface
    override fun gas(context: CarContext) {
        println("Car is speeding.")
    }

    override fun brake(context: CarContext) {
        context.state = StoppedState()
        println("Car slowing down.")
    }

    override fun turn(context: CarContext) {
        context.state = TurningState()
        println("Car is changing direction.")
    }
}

class StoppedState : ICarState {
    // Stopped State -> Car State interface
    override fun gas(context: CarContext) {
        context.state = GoingState()
        println("Car starts moving.")
    }

    override fun brake(context: CarContext) {
        println("Car stopped.")
    }

    override fun turn(context: CarContext) {
        println("Car can't turn while not moving.")
    }
}

class TurningState : ICarState {
    // Turning State -> Car State interface
    override fun gas(context: CarContext) {
        context.state = GoingState()
        println("Car started drifting!")
    }

    override fun brake(context: CarContext) {
        context.state = StoppedState()
        println("Car slowing down.")
    }

    override fun turn(context: CarContext) {
        println("Car going straight again.")
    }
}

// TEST
fun main(){
    val car = CarContext()
    car.accelerate()
    car.accelerate()
    car.steer()
    car.accelerate()
    car.decelerate()
    car.accelerate()
    car.decelerate()
    car.steer()
    car.decelerate()
}