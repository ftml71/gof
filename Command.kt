/**
A behavioral design pattern behavioral design
 pattern in which an object is used to 
encapsulate all information needed to 
perform an action or trigger an event 
at a later time.
**/

interface Command {
    fun execute()
}

class RaceOrganizer {
    private val queue = ArrayList<Command>()

    fun register(command: Command) {
        queue.add(command)
    }

    fun execute() {
        for (command in queue) {
            command.execute()
        }
    }
}

class RaceCommand(private val event: Event) : Command {
    override fun execute() {
        event.beginRace()
    }
}

class DetourCommand(private val event: Event) : Command {
    override fun execute() {
        event.takeDetour()
    }
}

class Event {
    fun beginRace() {
        println("Racing Begins")
    }

    fun takeDetour() {
        println("Changing track")
    }
}

fun main(){
    val event = Event()

    val raceCommand = RaceCommand(event)
    val detourCommand = DetourCommand(event)

    RaceOrganizer().apply {
        register(raceCommand)
        register(detourCommand)
        execute()
    }
}
