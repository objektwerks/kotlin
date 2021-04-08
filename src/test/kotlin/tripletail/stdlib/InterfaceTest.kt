package tripletail.stdlib

import org.junit.Test

interface Shape {
    fun draw(): String
}

class InterfaceTest {
    @Test fun interfaces() {
        class Circle : Shape {
            override fun draw(): String = "circle"
        }
        assert( Circle().draw() == "circle" )
    }
}