package creational

// Prototype Pattern creates objects with predefined attributes.
// This approach is mostly used in database applications.

interface Cloneable {
    // Prototype is the Database in our case
    fun clone(): Database
}

class Database: Cloneable {
    private var name: String? = null
    private val mTables = ArrayList<String>()

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    // Prototype of the table
    // if we don't make any changes new table will be exactly like this
    init {
        name = "Model"
        mTables.add("sysusers")
        mTables.add("sysindexes")
        mTables.add("sysfiles")
    }

    // Additional elements to be added to prototype table
    fun addTable(name: String) {
        mTables.add(name)
    }

    // Listing all elements from our table
    fun listTable() {
        for (i in 0 until mTables.size) {
            println(mTables[i])
        }
    }

    override fun clone(): Database {
        return Database()
    }
}

object PrototypeTest {
    @JvmStatic
    fun main(args: Array<String>){
        try {
            val md1 = Database()

            val db1 = md1.clone() as Database
            db1.setName("StockTrace")
            db1.addTable("Stocks")
            println(db1.getName())
            db1.listTable()

            println("-----------------")

            val db2 = md1.clone() as Database
            db2.setName("CRM")
            db2.addTable("Customers")
            println(db2.getName())
            db2.listTable()
        } catch (e: CloneNotSupportedException) {
            println(e.message)
        }
    }
}