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
        name = "model"
        mTables.add("users")
        mTables.add("indexes")
        mTables.add("files")
        mTables.add("preferences")
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
        println("-----------------")
    }

    override fun clone(): Database {
        return Database()
    }
}

// TEST
fun main(){
    try {
        val md = Database()

        val db1 = md.clone()
        db1.setName("Depot")
        db1.addTable("stocks")
        println(db1.getName())
        db1.listTable()

        val db2 = md.clone()
        db2.setName("Website")
        db2.addTable("admins")
        println(db2.getName())
        db2.listTable()
    } catch (e: CloneNotSupportedException) {
        println(e.message)
    }
}