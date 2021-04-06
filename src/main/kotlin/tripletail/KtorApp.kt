package tripletail

object KtorApp {
    @JvmStatic fun main(args : Array<String>) {
        args.forEach { arg -> println("Arg: $arg") }
        println("Ktor app todo!")
    }
}