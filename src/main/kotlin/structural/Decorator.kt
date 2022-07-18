package structural

// Decorator Pattern is like a wrapper to a class,
// adds new features without changing it.
// It adds a decorator class wrapping the original
// and implements new functionalities from there.

// Real life DP can be how we download new apps to
// our devices which makes our life easier.

abstract class Tab {
    constructor() : super ()
    var title: String? = ""

    constructor(title: String) {
        this.title = title
    }

    abstract fun apply()
}

class LoginTab(title: String) : Tab(title) {
    override fun apply() {
        println("$title titled login lab clicked.")
    }
}

class ExploreTab(title: String) : Tab(title) {
    override fun apply() {
        println("$title titled explore tab clicked.")
    }
}

abstract class TabDecorator (
    tab: Tab,
    title: String
) : Tab() {

    var tab: Tab

    init {
        this.tab = tab
        this.tab.title = title
    }
}

class AnimationsDecorator (
    tab: Tab,
    title: String
) : TabDecorator (tab, title) {

    override fun apply() {
        this.tab.apply()
    }

    fun scrollBy(animation: String) {
        apply()
        println("${tab.title} titled tab now has $animation animation.")
        println("---------------------------------")
    }
}

class ThemeDecorator (
    tab: Tab,
    title: String
) : TabDecorator (tab, title) {
    override fun apply() {
        this.tab.apply()
    }

    fun setTheme(theme: String) {
        apply()
        println("$theme theme applied to ${tab.title} titled tab.")
        println("---------------------------------")
    }
}

// TEST
fun main(){
    val t1 = LoginTab("Email")
    val t2 = ExploreTab("Inbox")
    val t3 = ExploreTab("Spam")

    val d1 = AnimationsDecorator(t2, "Inbox")
    val d2 = ThemeDecorator(t1, "Email")
    val d3 = ThemeDecorator(t3, "Spam")
    val d4 = ThemeDecorator(t2, "Inbox")

    d1.scrollBy("Fading")
    d2.setTheme("Dark")
    d3.setTheme("Light")
    d4.setTheme("Default")
}