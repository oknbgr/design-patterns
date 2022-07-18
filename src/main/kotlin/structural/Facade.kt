package structural

// Facade Pattern is provides a bridge between a client
// and a complex system using an interface for being more
// user-friendly.

// Real life implementation of FP could be Remote controls

class TVRemote private constructor() {
    private val sc: SubsystemChannel
    val sv: SubsystemVolume
    val sm: SubsystemMenu

    init {
        sv = SubsystemVolume()
        sc = SubsystemChannel()
        sm = SubsystemMenu()
    }

    fun nextChannel() {
        sc.next()
    }

    fun previousChannel() {
        sc.previous()
    }

    fun startTV(){
        SubsystemPower.on()
    }

    fun closeTV(){
        SubsystemPower.off()
    }

    fun openMenu(){
        sm.open()
    }

    fun closeMenu(){
        sm.close()
    }

    fun volumeUp(){
        sv.up()
    }

    fun volumeDown(){
        sv.down()
    }

    companion object {
        private val tvr: TVRemote? = null

        val current: TVRemote

        get() {
            return createFacade()
        }

        private fun createFacade() : TVRemote{
            var f = tvr

            if (f == null){
                f = TVRemote()
            }

            return f
        }
    }
}

class SubsystemVolume {
    fun up(){
        println("Volume::Up")
    }
    fun down(){
        println("Volume::Down")
    }
}

internal class SubsystemChannel {
    fun next(){
        println("Channel::Next")
    }
    fun previous(){
        println("Channel::Previous")
    }
}

class SubsystemMenu {
    fun open(){
        println("Menu::Open")
    }
    fun close(){
        println("Menu::Close")
    }
}

internal object SubsystemPower {
    fun on(){
        println("Power::On")
    }
    fun off(){
        println("Power::Off")
    }
}

// Test
fun main(){
    TVRemote.current.startTV()
    TVRemote.current.openMenu()
    TVRemote.current.nextChannel()
    TVRemote.current.nextChannel()
    TVRemote.current.closeMenu()
    TVRemote.current.volumeDown()
    TVRemote.current.volumeDown()
    TVRemote.current.volumeDown()
    TVRemote.current.closeTV()
}