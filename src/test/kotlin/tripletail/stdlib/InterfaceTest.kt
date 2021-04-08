package tripletail.stdlib

import org.junit.Test

enum class ShapeType { circle }

interface Shape {
    val typeOf: ShapeType
    fun draw(): String
}

class InterfaceTest {
    @Test fun interfaces() {
        class Circle : Shape {
            override val typeOf = ShapeType.circle
            override fun draw(): String = "circle"
        }
        assert( Circle().draw() == "circle" )
    }
}