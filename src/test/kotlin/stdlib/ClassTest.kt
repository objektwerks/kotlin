package stdlib

import kotlin.test.assertTrue

import org.junit.Test

typealias Name = String

class ClassTest {
    @Test fun classes() {
        class None
        assert( None().hashCode() > 0 )

        class Field(val name: String)
        assert( Field("field").name == "field" )
    }

    @Test fun primaryConstructor() {
        class Property constructor(val value: String)
        assert( Property("value").value == "value" )
    }

    @Test fun secondaryConstructor() {
        class Sink(val url: String) {
            constructor(protocol: String, path: String) : this("$protocol$path")
        }
        assert( Sink("file://", "home/temp").url.isNotEmpty() )
    }

    @Test fun init() {
        class Source(protocol: String, path: String) {
            val url: String?
            init {
                url = "$protocol$path"
            }
        }
        assert( !Source("file://", "home/temp").url.isNullOrEmpty() )
    }

    @Test fun typeAlias() {
        val name: Name = "Fred Flintstone"
        assertTrue( name.isNotEmpty() )
    }

    @Suppress("unused") // draw is NOT unused!
    @Test fun abstract() {
        abstract class Shape {
            abstract fun draw(): String
        }
        class Circle : Shape() {
            override fun draw(): String = "circle"
        }
        assert( Circle().draw() == "circle" )
    }

    @Test fun open() {
        open class Car(val model: String) {
            open fun drive(): String = "chugchugchug"
        }
        class Porsche(model: String) : Car(model) {
            override fun drive(): String = "purrrr"
        }

        val car = Car("Yugo")
        assert( car.model == "Yugo" )
        assert( car.drive() == "chugchugchug" )

        val porsche = Porsche("Boxster")
        assert( porsche.model == "Boxster" )
        assert( porsche.drive() == "purrrr" )
    }

    @Test fun objectLiteral() {
        val greeting = object {
            val hello = "Hello"
            val world = "Kotlin"
            fun message() = "$hello, $world!"
        }
        assert( greeting.message() == "Hello, Kotlin!")
    }

    sealed class Expr
    class Const(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
    object NotANumber : Expr()

    private fun eval(expr: Expr): Int =
        when (expr) {
            is Const -> expr.value
            is Sum -> eval(expr.right) + eval(expr.left)
            NotANumber -> Double.NaN.toInt()
        }

    @Test fun sealedClass() {
        assert( eval( Sum( Const(1), Const(2) ) ) == 3)
    }
}