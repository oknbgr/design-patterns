package structural

// Adapter Pattern is for using two incompatible interfaces together.
// Makes us no longer obliged to organise old codes.
// Since adopted class stays unchanged, we also don't need to
// work on optimizing our app where this class taken place.

// Real life usage of AP is chemists make compounds using
// different chemicals with certain techniques.

interface IBattery {
    // Returns remaining battery in MAH
    fun getRemaining(): Int
}

class Laptop : IBattery {
    override fun getRemaining(): Int {
        return 2500
    }
}

interface IBatteryAdapter {
    // Returns remaining battery in percentage
    fun getRemaining(): Int
}

class Battery : IBatteryAdapter {
    private val device = Laptop()

    override fun getRemaining(): Int {
        return convertMAHToPercentage(device.getRemaining())
    }

    private fun convertMAHToPercentage(remaining: Int): Int {
        return remaining * 100 / 4000
    }
}

// TEST
fun main(){
    val laptop = Laptop()
    val laptopAdapter = Battery()
    println(laptopAdapter.getRemaining())
}