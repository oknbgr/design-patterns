package creational

// Singleton pattern is used when only a single instance of an object is needed.
// This object gets used over and over again without creating a new one.
// Since it will be created once, its constructor has to protected (private) for preventing it to be directly called.

class Sample protected constructor() {
    private var mData: Int = 0

    fun getData(): Int {
        return mData
    }

    fun setData(value: Int) {
        mData = value
    }

    // It has to be both private and static
    // Because the address has to independent regardless of what object its carrying
    // In Kotlin, this mechanism is achieved by companion object
    companion object {
        private var mpSmp: Sample? = null

        // There needs to be function to return address of,
        // rather this created object,
        // or replace it if its already created.
        fun createObject(): Sample {
            if (mpSmp == null) {
                mpSmp = Sample()
            }

            return mpSmp as Sample
        }
    }
}

// TEST
fun main(){
    val o1 = Sample.createObject()
    val o2 = Sample.createObject()
    val o3 = Sample.createObject()

    o1.setData(1234)
    println("object 1 data: " + o1.getData())
    println("object 2 data: " + o2.getData())
    println("object 3 data: " + o3.getData())

    println("-------------------------")

    o2.setData(4321)
    println("object 1 data: " + o1.getData())
    println("object 2 data: " + o2.getData())
    println("object 3 data: " + o3.getData())
}