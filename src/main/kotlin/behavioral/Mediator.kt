package behavioral

// Mediator Pattern is useful when we need to connect objects
// even if they have no relation before at all.

// Real life example for MP can be meetings.
// We don't know every attendant to a meeting,
// but we can still communicate with them,
// even without introducing each other.

// Abstract Mediator
abstract class Room protected constructor() {
    // Room -- Participant
    protected var participants: ArrayList<Participant> = ArrayList()

    fun addParticipant(p: Participant){
        if (!participants.contains(p)){
            participants.add(p)
        }
    }

    abstract fun sendMessage(sender: Participant, receiver: Participant, message: String)
    abstract fun sendBroadcastMessage(sender: Participant, message: String)
}

// Concrete Mediator
class MeetingRoom : Room() {
    // Meeting Room -> Room
    override fun sendBroadcastMessage(sender: Participant, message: String) {
        for (p in this.participants){
            p.receiveMessage(sender, message)
        }
    }

    override fun sendMessage(sender: Participant, receiver: Participant, message: String) {
        receiver.receiveMessage(sender, message)
    }
}

// Abstract Colleague
abstract class Participant protected constructor(nickname: String) {
    // Participant -- Room
    var nickname: String

    init {
        this.nickname = nickname
    }

    abstract fun receiveMessage(sender: Participant, message: String)
}

// Concrete Colleague #1
class BasicParticipant(nickname: String) : Participant(nickname) {
    // Basic Participant -> Participant
    override fun receiveMessage(sender: Participant, message: String) {
        println("${sender.nickname} -> ${this.nickname}: $message")
    }
}

// Concrete Colleague #2
class Admin(nickname: String) : Participant(nickname) {
    // Admin -> Participant
    override fun receiveMessage(sender: Participant, message: String) {
        println("(${javaClass.simpleName}) ${sender.nickname} -> ${this.nickname}: $message")
    }
}

// TEST
fun main(){
    val p1 = Admin("Cal")
    val p2 = BasicParticipant("Cere")
    val p3 = BasicParticipant("Jaro")
    val p4 = BasicParticipant("Greez")
    val p5 = BasicParticipant("Eno")

    val mr = MeetingRoom()
    mr.addParticipant(p1)
    mr.addParticipant(p2)
    mr.addParticipant(p3)
    mr.addParticipant(p4)
    mr.addParticipant(p5)

    mr.sendBroadcastMessage(p1, "Welcome to the meeting!")

    println("-------------------------------")

    mr.sendMessage(p5, p3, "Android is so cool.")
    mr.sendMessage(p3, p5, "Hmm ok...")

    println("-------------------------------")

    mr.sendMessage(p4, p2, "Iphones are expensive.")
    mr.sendMessage(p2, p4, "Definitely!")
}