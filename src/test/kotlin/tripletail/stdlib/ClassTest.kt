package tripletail.stdlib

import org.junit.Test
import kotlin.test.assertTrue

typealias Name = String

class ClassTest {
    @Test fun classes() {
        val name: Name = "Fred Flintstone"
        assertTrue( name.isNotEmpty() )

        class None
        assert( None().hashCode() > 0 )

        class Field(val name: String)
        assert( Field(name = "field").name == "field" )

        class Property constructor(val value: String)
        assert( Property(value = "value").value == "value" )

        class Source(val protocol: String, val path: String) {
            val url: String?
            init {
                url = "$protocol$path"
            }
        }
        assert( !Source(protocol = "file://", path = "home/temp").url.isNullOrEmpty() )

        class Sink(val url: String) {
            constructor(protocol: String, path: String) : this("$protocol$path")
        }
        assert( Sink(protocol = "file://", path = "home/temp").url.isNotEmpty() )

        abstract class Shape {
            abstract fun draw(): String
        }
        class Circle : Shape() {
            override fun draw(): String = "circle"
        }
        assert( Circle().draw() == "circle" )

        open class Car {
            open fun drive(): String = ""
        }
        class Porsche : Car() {
            override fun drive(): String {
                super.drive()
                return "purrrr"
            }
        }
        assert( Porsche().drive() == "purrrr" )

        val greeting = object {
            val hello = "Hello"
            val world = "Kotlin"
            fun message() = "$hello, $world!"
        }
        assert( greeting.message() == "Hello, Kotlin!")
    }
}