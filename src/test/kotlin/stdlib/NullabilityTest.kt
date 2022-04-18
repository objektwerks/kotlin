package stdlib

import kotlin.test.assertNull
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

import org.junit.Test

data class Task(val description: String, val code: String? = null)

class NullabilityTest {
    @Test fun nullability() {
        val text: String? = null
        assert( text.isNullOrEmpty() )
        assert( (text?.length ?: -1) == -1)
        assertFailsWith<NullPointerException> {
            text!!.length
        }

        val value = "value"
        assert( value.isNotEmpty() )

        val task = Task("Mow yard.")
        assertNull( task.code )
        assertNotNull( task.copy(code = "completed").code )

        val numbers: List<Int?> = listOf(1, 2, 3, null)
        var squares = listOf<Int?>()
        for (item in numbers) {
            item?.let { squares = squares.plus(it * it); it }
            ?.also { println("number: $it") }
        }
        assert( squares == listOf( 1, 4, 9) )
        assert( numbers.filterNotNull() == listOf( 1, 2, 3) )
    }
}