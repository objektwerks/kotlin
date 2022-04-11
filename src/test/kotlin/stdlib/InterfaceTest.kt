package stdlib

import org.junit.Test
import kotlin.test.assertTrue

enum class ShapeType { circle }

interface Shape {
    val typeOf: ShapeType
    fun draw(): String
}

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

class InterfaceTest {
    @Test fun interfaces() {
        class Circle : Shape {
            override val typeOf = ShapeType.circle
            override fun draw(): String = "circle"
        }
        assert( Circle().draw() == "circle" )

        val isEven = IntPredicate { it % 2 == 0 }
        assertTrue( isEven.accept(2) )
    }
}