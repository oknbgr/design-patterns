package creational

// Abstract Factory Pattern utilizes focusing on concrete product and abstraction of producer.
// Main idea behind this pattern is hiding (abstracting) factory which product (object) came from.

// Real life example can be buying a phone without caring how and where it produced.

class FactoryUtil {
    // User -> Database Factory
    // User -> Connection
    // User -> Command

    companion object {
        @Throws(Exception::class)
        fun getFactory(databaseName: String) : DatabaseFactory {
            return when (databaseName) {
                "SQLite" -> {
                    SQLiteFactory()
                }
                "Firebase" -> {
                    FirebaseFactory()
                }
                "Room" -> {
                    RoomFactory()
                }
                else -> {
                    throw Exception("ERROR! Database name not recognized.")
                }
            }
        }
    }
}

// ABSTRACT FACTORY
abstract class DatabaseFactory {
    abstract fun createConnection(): Connection
    abstract fun createCommand(): Command
}

// CONCRETE FACTORIES
class SQLiteFactory : DatabaseFactory() {
    // SQLite Factory -> Database Factory
    // SQLite Factory -> SQLite Connection
    // SQLite Factory -> SQLite Command

    override fun createCommand(): Command {
        return SQLiteCommand()
    }
    override fun createConnection(): Connection {
        return SQLiteConnection()
    }
}

class FirebaseFactory : DatabaseFactory() {
    // Firebase Factory -> Database Factory
    // Firebase Factory -> Firebase Connection
    // Firebase Factory -> Firebase Command

    override fun createCommand(): Command {
        return FirebaseCommand()
    }
    override fun createConnection(): Connection {
        return FirebaseConnection()
    }
}

class RoomFactory : DatabaseFactory() {
    // Room Factory -> Database Factory
    // Room Factory -> Room Connection
    // Room Factory -> Room Command

    override fun createCommand(): Command {
        return RoomCommand()
    }
    override fun createConnection(): Connection {
        return RoomConnection()
    }
}

// ABSTRACT PRODUCTS
abstract class Connection {
    protected abstract var log: String
    abstract fun connect()
}

abstract class Command {
    abstract var query: String
    abstract fun execute()
}

// CONCRETE PRODUCTS
class SQLiteConnection : Connection() {
    // SQLite Connection -> Connection

    override var log: String
        get() {
            return log
        }
        set(value) {
            log = value
        }

    override fun connect() {
        println("Connected to SQLite!")
    }
}

class FirebaseConnection : Connection() {
    // Firebase Connection -> Connection

    override var log: String
        get() {
            return log
        }
        set(value) {
            log = value
        }

    override fun connect() {
        println("Connected to Firebase!")
    }
}

class RoomConnection : Connection() {
    // Room Connection -> Connection

    override var log: String
        get() {
            return log
        }
        set(value) {
            log = value
        }

    override fun connect() {
        println("Connected to Room!")
    }
}

class SQLiteCommand : Command() {
    // SQLite Command -> Command

    override var query: String = ""
        get() {
            return this.query
        }
        set(value) {
            field = value
        }

    override fun execute() {
        println("SQLite ran.")
    }
}

class FirebaseCommand : Command() {
    // Firebase Command -> Command

    override var query: String = ""
        get() {
            return this.query
        }
        set(value) {
            field = value
        }

    override fun execute() {
        println("Firebase ran.")
    }
}

class RoomCommand : Command() {
    // Firebase Command -> Command

    override var query: String = ""
        get() {
            return this.query
        }
        set(value) {
            field = value
        }

    override fun execute() {
        println("Room ran.")
    }
}

fun main(){
    try {
        var db = FactoryUtil.getFactory("SQLite")

        var cnn = db.createConnection()
        cnn.connect()

        var cmd = db.createCommand()
        cmd.query = "Select * from table"
        cmd.execute()

        println("\n--------------------\n")

        db = FactoryUtil.getFactory("Firebase")

        cnn = db.createConnection()
        cnn.connect()

        cmd = db.createCommand()
        cmd.query = "Select * from table"
        cmd.execute()

        println("\n--------------------\n")

        db = FactoryUtil.getFactory("Room")

        cnn = db.createConnection()
        cnn.connect()

        cmd = db.createCommand()
        cmd.query = "Select * from table"
        cmd.execute()
    } catch (e: Exception) {
        println(e.message)
    }
}