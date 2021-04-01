package tripletail

object App {
    fun greeting() = "Hello, Kotlin!"

    @JvmStatic fun main(args : Array<String>) {
        println(args)
        println(greeting())
    }
}