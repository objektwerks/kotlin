package tripletail.stdlib

import kotlin.test.assertNull

import org.junit.Test
import kotlin.test.assertFailsWith

import kotlin.test.assertNotNull

data class Task(val description: String, val code: String? = null)

class NullabilityTest {
    @Test fun nullability() {
        var text: String? = null
        assert(text.isNullOrEmpty())
        assert( text?.length ?: -1 == -1 )
        assertFailsWith<NullPointerException> {
            text!!.length
        }

        text = "text"
        assert( text.isNotEmpty() )

        val task = Task("Mow yard.")
        assertNull( task.code )
        assertNotNull( task.copy(code = "completed").code )

        val numbers: List<Int?> = listOf(1, 2, 3, null)
        var squares = listOf<Int?>()
        for (item in numbers) {
            item?.let { squares = squares.plus(it * it); it }
            ?.also{it -> println("number: $it") }
        }
        assert( squares == listOf( 1, 4, 9) )
        assert( numbers.filterNotNull() == listOf( 1, 2, 3) )
    }
}