package behavioral

// State Pattern is for tracking meaningful changes in object's data
// Which cause behavioral changes in object itself.
// By that way, object can behave like it changed its class.

// Real life example of SP can be how we put our phones in silent, vibrate, sound modes.

interface IThreadState {
    fun run(context: ThreadContext)
    fun stop(context: ThreadContext)
    fun wait(context: ThreadContext)
}

class ThreadContext {
    // Thread Context -> Thread State interface
    var state: IThreadState

    init {
        println("Thread is created and not running yet.")
        state = StoppedState()
    }

    fun start(){
        state.run(this)
    }

    fun abort(){
        state.stop(this)
    }

    fun sleep(){
        state.wait(this)
    }
}

class RunningState : IThreadState {
    // Running State -> Thread State interface
    override fun run(context: ThreadContext) {
        println("Thread is already running.")
    }

    override fun stop(context: ThreadContext) {
        context.state = StoppedState()
        println("Thread stopped.")
    }

    override fun wait(context: ThreadContext) {
        context.state = WaitingState()
        println("Thread is temporarily put out of schedule.")
    }
}

class StoppedState : IThreadState {
    // Stopped State -> Thread State interface
    override fun run(context: ThreadContext) {
        context.state = RunningState()
        println("Thread started to run.")
    }

    override fun stop(context: ThreadContext) {
        println("Thread is already stopped.")
    }

    override fun wait(context: ThreadContext) {
        println("Cannot put on wait a stopped thread.")
    }
}

class WaitingState : IThreadState {
    // Waiting State -> Thread State interface
    override fun run(context: ThreadContext) {
        context.state = RunningState()
        println("Thread put on schedule while it was waiting.")
    }

    override fun stop(context: ThreadContext) {
        context.state = StoppedState()
        println("Waiting thread is now fully stopped.")
    }

    override fun wait(context: ThreadContext) {
        println("Thread is already waiting.")
    }
}

// TEST
fun main(){
    val thread = ThreadContext()
    thread.start()
    thread.start()
    thread.sleep()
    thread.start()
    thread.abort()
    thread.start()
    thread.abort()
    thread.abort()
}