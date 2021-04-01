package tripletail

object App {
    fun greeting() = "Hello, Kotlin!"

    @JvmStatic fun main(args : Array<String>) {
        args.forEach { arg -> println("Arg: ${arg}") }
        println("Greeting: ${greeting()}")
    }
}