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
object SingletonTest {
    @JvmStatic
    fun main(args: Array<String>){
        val obj1 = Sample.createObject()
        val obj2 = Sample.createObject()
        val obj3 = Sample.createObject()

        obj1.setData(1234)
        println("obj1 data: " + obj1.getData())
        println("obj2 data: " + obj2.getData())
        println("obj3 data: " + obj3.getData())

        println("-------------------------")

        obj2.setData(4321)
        println("obj1 data: " + obj1.getData())
        println("obj2 data: " + obj2.getData())
        println("obj3 data: " + obj3.getData())
    }
}