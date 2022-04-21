package stdlib

import kotlin.test.assertTrue

import org.junit.Test

class InterfaceTest {
    enum class ShapeType { circle }

    interface Shape {
        val typeOf: ShapeType
        fun draw(): String
    }

    @Test
    fun byInterface() {
        class Circle : Shape {
            override val typeOf = ShapeType.circle
            override fun draw(): String = "circle"
        }
        assert(Circle().draw() == "circle")
    }

    fun interface IntPredicate {
        fun assert(i: Int): Boolean
    }

    @Test
    fun functionInterface() {
        val isEven = IntPredicate { it % 2 == 0 } // SAM conversion!
        assertTrue( isEven.assert(2) )
    }
}