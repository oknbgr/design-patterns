package structural

// Composite Pattern is for treating a group of objects
// as if they are a single object.
// Objects get composed like a tree structure.

// Real life example of CP is obviously a tree and its leafs.

class Soldier(name: String, rank: String) {
    private var name: String = ""
    private var rank: String = ""
    private var army: ArrayList<Soldier>

    init {
        this.name = name
        this.rank = rank
        army = ArrayList<Soldier>()
    }

    fun add(s: Soldier){
        army.add(s)
    }

    fun remove(s: Soldier){
        army.remove(s)
    }

    fun getArmy() : ArrayList<Soldier> {
        return army
    }

    override fun toString(): String {
        return "$rank $name"
    }
}

fun main(){
    val general = Soldier("Jake Dunn", "General")

    val colonel = Soldier("Martin Hawker", "Colonel")

    val major1 = Soldier("Richard Morrison", "Major")
    val major2 = Soldier("Seaman Collins", "Major")

    val captain1 = Soldier("Clearance Strictland", "Captain")
    val captain2 = Soldier("Edward Jones", "Captain")
    val captain3 = Soldier("David Rosenthal", "Captain")
    val captain4 = Soldier("Jacob Hargreave", "Captain")

    general.add(colonel)

    colonel.add(major1)
    colonel.add(major2)

    major1.add(captain1)
    major1.add(captain2)

    major2.add(captain3)
    major2.add(captain4)

    println(general)

    println("---------------")

    for (col in general.getArmy()){
        println(col)
        println("---------------")

        for (mjr in colonel.getArmy()){
            println(mjr)
            println("---------------")

            for (cap in major1.getArmy().plus(major2.getArmy())){
                println(cap)
            }

            println("---------------")
        }
    }
}