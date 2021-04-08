package tripletail.stdlib

import org.junit.Test

class ClassTest {
    @Test fun classes() {
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
    }
}