package behavioral

// Command Pattern basically provides a standard solution
// to distinguish between the objects which WANTS to do some operation
// and which WILL do the operation

// Difference between Chain of Responsibility and Command Pattern is
// CRP gives same task to all objects on in the chain.
// CP gives task only to a particular object which can perform the operation.

// Real life example of this pattern can be how ATMs work.

class SocialMediaApp {
    fun like(){
        println("User liked your post.")
    }
    fun comment(){
        println("User commented on your post.")
    }
    fun follow(){
        println("User followed you.")
    }
}

// Abstract Command
interface SendRequest {
    fun getResponse()
}

// Concrete Commands
class Like : SendRequest {
    // Like -> Send Request

    private var app: SocialMediaApp? = null

    init {
        app = SocialMediaApp()
    }

    override fun getResponse() {
        app?.like()
    }
}
class Comment : SendRequest {
    // Comment -> Send Request

    private var app: SocialMediaApp? = null

    init {
        app = SocialMediaApp()
    }

    override fun getResponse() {
        app?.comment()
    }
}
class Follow : SendRequest {
    // Follow -> Send Request

    private var app: SocialMediaApp? = null

    init {
        app = SocialMediaApp()
    }

    override fun getResponse() {
        app?.follow()
    }
}

// Invoker
class AppUI {
    // App UI -> Send Request

    private var requests = HashMap<String, SendRequest>()

    init {
        requests["Comment"] = Comment()
        requests["Follow"] = Follow()
        requests["Like"] = Like()
    }

    fun sendRequests(commandName: String){
        requests[commandName]?.getResponse()
    }
}

// TEST
fun main(){
    val app = AppUI()
    app.sendRequests("Comment")
    app.sendRequests("Like")
    app.sendRequests("Follow")
}