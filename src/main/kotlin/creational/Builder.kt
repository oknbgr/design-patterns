package creational

// Builder Pattern literally builds anything we wish to.
// There are both necessary and extra parts exist in this pattern.
// Necessary attributes are built in creation of the object,
// extras are dependent on user's decision.

// Real life example of this pattern could be how cars are built.
// A car must have an engine and tires but keyless entry is extra.

enum class Brands(val id: Int){
    BMW(1),
    AUDI(2)
}

object Customer {
    // Customer -> Agency -> Factory
    // Customer goes to an agency to buy their car.

    fun requestCar(brand: Brands, model: String, vararg requestedParts: String) {
        val agency = Agency()

        when (brand) {
            Brands.AUDI -> {
                val a = agency.requestAudi(
                    model,
                    *requestedParts
                )
                println(a.toString())
            }
            Brands.BMW -> {
                val b = agency.requestBMW(
                    model,
                    *requestedParts
                )
                println(b.toString())
            }
        }
    }
}

class Agency {
    // Agency contacts factory and requests cars depending on customer's decisions

    private var factory: Factory? = null

    fun requestBMW(model: String, vararg requests: String) : BMW {
        factory = BMWFactory()

        for (request in requests){
            factory!!.addPart(request)
        }

        return factory!!.produce(model) as BMW
    }

    fun requestAudi(model: String, vararg requests: String) : Audi {
        factory = AudiFactory()

        for (request in requests){
            factory!!.addPart(request)
        }

        return factory!!.produce(model) as Audi
    }
}

abstract class Factory {
    // Factory produces cars with necessary and optional parts.
    abstract fun addPart(part: String)
    abstract fun produce(model: String): Car
}

class AudiFactory : Factory(){
    // Audi Factory -> Factory
    // Audi Factory -> Audi

    private val audi = Audi()

    override fun addPart(part: String) {
        audi.optionalParts!!.add(part)
    }

    override fun produce(model: String): Car {
        audi.set(model)
        return audi
    }
}

class BMWFactory : Factory(){
    // BMW Factory -> Factory
    // BMW Factory -> BMW

    private val bmw = BMW()

    override fun addPart(part: String) {
        bmw.optionalParts!!.add(part)
    }

    override fun produce(model: String): Car {
        bmw.set(model)
        return bmw
    }
}

abstract class Car {
    // All cars have to be built with necessary parts and optional parts are added on special request.
    // For this reason, optionalParts array list is nullable.

    protected var necessaryParts: ArrayList<String>
    var optionalParts: ArrayList<String>? = null
    protected var model: String? = null

    init {
        // Necessary parts
        val np = arrayOf("Engine", "Drivetrain", "Tires", "Bumpers", "Doors", "Exhaust", "Headlights", "Taillights")

        necessaryParts = ArrayList()

        for (p in np) {
            necessaryParts.add(p)
        }

        optionalParts = ArrayList()
    }
}

class Audi : Car() {
    // Audi -> Car

    fun get(): String? {
        return super.model
    }

    fun set(model: String?){
        super.model = model
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(String.format("AUDI $model\n\r"))

        for (i in 0 until necessaryParts.size){
            sb.append(necessaryParts[i] + "\n\r")
        }

        for (i in 0 until super.optionalParts!!.size){
            sb.append(super.optionalParts!![i] + "\n\r")
        }

        return sb.toString()
    }
}

class BMW : Car() {
    // BMW -> Car

    fun get(): String? {
        return super.model
    }

    fun set(model: String?){
        super.model = model
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(String.format("BMW $model\n\r"))

        for (i in 0 until necessaryParts.size){
            sb.append(necessaryParts[i] + "\n\r")
        }

        for (i in 0 until super.optionalParts!!.size){
            sb.append(super.optionalParts!![i] + "\n\r")
        }

        return sb.toString()
    }
}

object BuilderTest {
    @JvmStatic
    fun main(args: Array<String>){
        Customer.requestCar(
            Brands.BMW,
            "M4",
            "Ceramic Brakes", "Forged Wheels", "Carbon Fibre Spoiler"
            )

        println("--------------------------")

        Customer.requestCar(
            Brands.AUDI,
            "RS7",
            "Gray Metallic Paint", "Bose Sound System", "Keyless Entry"
        )
    }
}