package creational

// Builder Pattern literally builds anything we wish to.
// There are both necessary and extra parts exist in this pattern.
// Necessary attributes are built in creation of the object,
// extras are dependent on user's decision.

// Real life example of this pattern could be how cars are built.
// A car must have an engine and tires but keyless entry is extra.

enum class Brands {
    BMW,
    MERCEDES
}

object Customer {
    // Customer -> Agency -> Factory
    // Customer goes to an agency to buy their car.

    fun requestCar(brand: Brands, model: String, vararg requestedParts: String) {
        val agency = Agency()

        when (brand) {
            Brands.MERCEDES -> {
                val m = agency.requestMercedes(
                    model,
                    *requestedParts
                )
                println(m.toString())
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

    fun requestMercedes(model: String, vararg requests: String) : Mercedes {
        factory = MercedesFactory()

        for (request in requests){
            factory!!.addPart(request)
        }

        return factory!!.produce(model) as Mercedes
    }
}

abstract class Factory {
    // Factory produces cars with necessary and optional parts.
    abstract fun addPart(part: String)
    abstract fun produce(model: String): Car
}

class MercedesFactory : Factory(){
    // Mercedes Factory -> Factory
    // Mercedes Factory -> Mercedes

    private val m = Mercedes()

    override fun addPart(part: String) {
        m.optionalParts!!.add(part)
    }

    override fun produce(model: String): Car {
        m.set(model)
        return m
    }
}

class BMWFactory : Factory(){
    // BMW Factory -> Factory
    // BMW Factory -> BMW

    private val b = BMW()

    override fun addPart(part: String) {
        b.optionalParts!!.add(part)
    }

    override fun produce(model: String): Car {
        b.set(model)
        return b
    }
}

abstract class Car {
    // All cars have to be built with necessary parts and optional parts are added on special request.
    // For this reason, optionalParts array list is nullable.

    private var necessaryParts: ArrayList<String>
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

    override fun toString(): String {
        val sb = StringBuilder()

        if (model != null){
            sb.append(String.format("${javaClass.simpleName} $model\n\r"))
        } else {
            sb.append(String.format("${javaClass.simpleName}\n\r"))
        }

        sb.append("\nNecessary Parts:\n")
        for (i in 0 until necessaryParts.size){
            sb.append(necessaryParts[i] + "\n\r")
        }

        if (optionalParts != null){
            sb.append("\nOptions:\n")

            for (i in 0 until optionalParts!!.size){
                sb.append(optionalParts!![i] + "\n\r")
            }
        }

        sb.append("------------------------------\n")

        return sb.toString()
    }
}

class Mercedes : Car() {
    // Mercedes -> Car

    fun get(): String? {
        return super.model
    }

    fun set(model: String?){
        super.model = model
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
}

// TEST
fun main(){
    Customer.requestCar(
        Brands.BMW,
        "M4",
        "Ceramic Brakes", "Forged Wheels", "Carbon Fibre Spoiler"
    )

    Customer.requestCar(
        Brands.MERCEDES,
        "AMG GT",
        "Gray Metallic Paint", "Burmester Sound System", "Keyless Entry"
    )
}