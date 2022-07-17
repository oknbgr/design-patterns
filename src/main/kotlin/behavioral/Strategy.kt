package behavioral

// Strategy Pattern is implemented by using temporary tools
// and sticking to a single solution way among multiple solutions
// for solving a problem.

// Real life example of SP can be how we transport in the city,
// We can take the bus, metro or taxi. There are multiple solutions
// for one problem.

// Abstract Strategy
interface Brush {
    fun paint()
}

// Concrete Strategy Classes
class SolidBrush : Brush {
    // Solid Brush -> Brush
    override fun paint() {
        println("Paint with single color")
    }
}

class LinearGradientBrush : Brush {
    // Linear Gradient Brush -> Brush
    override fun paint() {
        println("Linear paint with multiple colors")
    }
}

class RadialGradientBrush : Brush {
    // Radial Gradient Brush -> Brush
    override fun paint() {
        println("Radial paint with multiple colors")
    }
}

// Context
class Rectangle(b: Brush){
    // Rectangle -> Brush
    private var b: Brush

    init {
        this.b = b
    }

    fun draw(){
        b.paint()
        println("Rectangle")
    }
}

class Circle(b: Brush){
    // Circle -> Brush
    private var b: Brush

    init {
        this.b = b
    }

    fun draw(){
        b.paint()
        println("Circle")
    }
}

// TEST
fun main(){
    val b1 = SolidBrush()
    val b2 = LinearGradientBrush()
    val b3 = RadialGradientBrush()

    val r1 = Rectangle(b1)
    r1.draw()

    println("----------------------------------")

    val r2 = Rectangle(b2)
    r2.draw()

    println("----------------------------------")

    val c = Circle(b3)
    c.draw()
}