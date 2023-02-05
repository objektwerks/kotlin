package console

import java.time.Instant

class App {
    companion object {
        private fun greeting(): String = "Kotlin!"

        @JvmStatic fun main(args : Array<String>) {
            args.forEach { arg -> println("Arg: $arg") }
            println("Greetings, ${greeting()}")
            println("Datetime: ${Instant.now()}")
        }
    }
}