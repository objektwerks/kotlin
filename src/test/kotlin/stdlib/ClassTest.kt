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
        class Source(val protocol: String, val path: String) {
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
    }

    @Test fun objectLiteral() {
        val greeting = object {
            val hello = "Hello"
            val world = "Kotlin"
            fun message() = "$hello, $world!"
        }
        assert( greeting.message() == "Hello, Kotlin!")
    }
}