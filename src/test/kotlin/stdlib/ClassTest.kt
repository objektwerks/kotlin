package stdlib

import java.time.Instant

import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.math.absoluteValue
import kotlin.random.Random

import org.junit.Test

typealias Name = String

object DateTime {
    fun now(): String = Instant.now().toString()
}

class RandomIntGenerator {
    companion object {
        fun int(): Int = Random(10).nextInt().absoluteValue
    }
}

@JvmInline value class IntValue(val int: Int)

@JvmRecord data class Person(val name: String, val age: Int)

class ClassTest {
    @Test fun classes() {
        class None

        assert( None().hashCode() > 0 )

        class Field(val name: String)

        assert( Field("field").name == "field" )
    }

    @Test fun primaryConstructor() {
        class Property(val value: String)

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
            var url: String = ""
            init {
                url = "$protocol$path"
            }
        }

        assert( Source("file://", "home/temp").url.isNotEmpty() )
    }

    @Test fun typeAlias() {
        val name: Name = "Fred Flintstone"
        assertTrue( name.isNotEmpty() )
    }

    @Suppress("unused") // draw is used!
    @Test fun abstract() {
        abstract class Shape {
            abstract fun draw(): String
        }

        class Circle : Shape() {
            override fun draw(): String = "circle"
        }

        assert( Circle().draw() == "circle" )
    }

    @Test fun inner() {
        class A {
            inner class Inner(val id: String = "Inner.A")
        }

        class B {
            inner class Inner(val id: String = "Inner.B")
        }

        assert( A().Inner().id == "Inner.A" )
        assert( B().Inner().id == "Inner.B" )
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

        open class Rectangle {
            open fun draw(): String = "base"
        }

        class FilledRectangle : Rectangle() {
            override fun draw(): String = super.draw() + "-filled"
        }

        assert( FilledRectangle().draw() == "base-filled" )
    }

    @Test fun value() {
        assert( IntValue(1).int == 1 ) // @JvmInline, unboxed!
    }

    @Test fun dataClass() {
        data class Data(val value: String)

        val data = Data("data")
        val copy = data.copy(value = "copy")
        val ( destructuredValue ) = copy
        assertEquals( copy.value, destructuredValue )
        assertNotEquals( data, copy )
    }

    @Test fun jvmRecord() {
        val person = Person("fred flintstone", 24) // See definition above!
        val (name, age) = person
        assert( name == "fred flintstone" )
        assert( age == 24 )
        assert( person.hashCode() != 0 )
        assert( person.copy(age = 25) != person )
    }

    @Test fun objectLiteral() {
        val greeting = object {
            val hello = "Hello"
            val world = "Kotlin"
            fun message() = "$hello, $world!"
        }

        assert( greeting.message() == "Hello, Kotlin!" )
    }

    @Test fun objectSingleton() {
        assert( DateTime.now().isNotEmpty() ) // See definition above!
    }

    @Test fun objectCompanion() {
        assert( RandomIntGenerator.int() > 0 ) // See definition above!
    }

    sealed class Expr
    class Const(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
    data object NotANumber : Expr()

    private fun eval(expr: Expr): Int =
        when (expr) {
            is Const -> expr.value
            is Sum -> eval(expr.right) + eval(expr.left)
            NotANumber -> Double.NaN.toInt()
        }

    @Test fun sealed() {
        assert( eval( Sum( Const(1), Const(2) ) ) == 3 )
    }

    interface Car {
        fun race(): String
    }
    class SportsCar : Car {
        override fun race(): String = "prrrr"
    }
    class Porsche : Car by SportsCar() // Porsche extends Car, which then delegates to the SportsCar race function.

    @Test fun delegation() {
        assert( Porsche().race() == "prrrr" )
    }
}