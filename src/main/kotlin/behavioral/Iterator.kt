package behavioral

// Iterator (Cursor) Pattern lets us traverse sub-objects of a composite object (such as lists),
// without needing to having information about sub-objects.

class Employee(name: String, title: String) {
    private val name: String
    private val title: String

    init {
        this.name = name
        this.title = title
    }

    override fun toString(): String {
        return "$name: $title"
    }
}

// Generic iterator interface for different data structures
interface Iterator {
    fun getItem(): Any
    fun next(): Boolean
}

class ListIterator : Iterator {
    // List Iterator -> Iterator

    private val pr = PRDepartment()
    private var i = 0

    override fun getItem(): Any {
        val employee = pr.getEmployees()[i]
        i++
        return employee
    }

    override fun next(): Boolean {
        return i < pr.getEmployees().size
    }
}

class ArrayIterator: Iterator {
    // Array Iterator -> Iterator

    private val hr = HRDepartment()
    private var i = 0

    override fun getItem(): Any {
        val employee = hr.getEmployees()[i]
        i++
        return employee
    }

    override fun next(): Boolean {
        return i < hr.getEmployees().size
    }
}

// Generic iterable interface for different data structures
interface Iterable {
    // Iterable -> Iterator
    val iterator: Iterator
}

class HRDepartment: Iterable {
    // Human Resources Department -> Iterable

    // Employees of HR are stored in an array
    private val employees: Array<Employee>

    override val iterator: Iterator
        get() {
            return ArrayIterator()
        }

    init {
        employees = arrayOf(
            Employee("John Doe", "Instructor"),
            Employee("Jack Miller", "Director"),
            Employee("Ellie Williams", "Recruiter")
        )
    }

    fun getEmployees(): Array<Employee> {
        return employees
    }
}

class PRDepartment: Iterable {
    // Public Relations Department -> Iterable

    // Employees of PR are stored in an arraylist
    private val employees: ArrayList<Employee> = ArrayList()

    override val iterator: Iterator
        get() {
            return ListIterator()
        }

    init {
        employees.add(Employee("Delsin Rowe", "Intern"))
        employees.add(Employee("Abigail Walker", "Copywriter"))
        employees.add(Employee("Eugene Sims", "Social Media Specialist"))
    }

    fun getEmployees(): ArrayList<Employee> {
        return employees
    }
}

// TEST
fun main(){
    // HR and PR employees are stored in different type of data structures.
    // But we can both traverse them using the Iterator interface.

    val hr = HRDepartment()
    val iterator = hr.iterator

    while (iterator.next()) {
        println(iterator.getItem().toString())
    }

    println("\n----------------------\n")

    val pr = PRDepartment()
    val iterator2 = pr.iterator

    while (iterator2.next()){
        println(iterator2.getItem().toString())
    }
}