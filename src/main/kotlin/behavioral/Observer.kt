package behavioral

// Observer (Publish-Subscribe) Pattern implements behavior of
// Reactive Objects which automatically notifies
// all the objects observing them. And then,
// Observer Objects update their status according to the
// notification they received.

// Real life example for OP can be how a scientists observe in their experiments.

interface IObservable {
    // Observable interface -> Control
    fun register(c: Control)
    fun unregister(c: Control)
    fun sendNotification()
}

class Stock: IObservable {
    // Stock -> Observable interface

    private var mCount: Int = 0
    private val controller: ArrayList<Control> = ArrayList()

    init {
        mCount = 10
    }

    fun pullElement() {
        --mCount
    }

    override fun register(c: Control) {
        controller.add(c)
    }

    override fun unregister(c: Control) {
        controller.remove(c)
    }

    override fun sendNotification() {
        for (i in 0 until controller.size) {
            controller[i].onAction(mCount.toString())
        }
    }
}

abstract class Control {
    var text: String = ""
    var name: String = ""

    // Will be called when state changes
    abstract fun onAction(value: String)

    // Binding and unbinding
    abstract fun add(o: IObservable)
    abstract fun remove(o: IObservable)
}

class Label: Control() {
    // Label -> Control

    override fun add(o: IObservable) {
        o.register(this)
    }

    override fun remove(o: IObservable) {
        o.unregister(this)
    }

    override fun onAction(value: String) {
        text = value
    }
}

fun display(c: Control) {
    println("${c.name}: ${c.text}")
}

// TEST
fun main(){
    val l1 = Label()
    l1.name = "l1"

    val l2 = Label()
    l2.name = "l2"

    // Registering controller
    val s = Stock()
    s.register(l1)
    s.register(l2)

    // Tests
    s.sendNotification()
    display(l1)
    display(l2)

    println("\n----------------\n")

    s.pullElement()

    s.sendNotification()
    display(l1)
    display(l2)

    s.pullElement()

    println("\n----------------\n")

    s.sendNotification()
    display(l1)
    display(l2)
}