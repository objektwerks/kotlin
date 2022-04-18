package stdlib

import kotlin.test.assertFailsWith

import org.junit.Test

class NullabilityTest {
    @Test fun nullable() {
        val text: String? = null
        assert(text.isNullOrEmpty())
        assert((text?.length ?: -1) == -1)
        assertFailsWith<NullPointerException> {
            text!!.length
        }
    }

    @Test fun notNullable() {
        val value = "value"
        assert(value.isNotEmpty())
    }

    @Test fun letNullable() {
        val numbers: List<Int?> = listOf(1, 2, 3, null)
        var squares = listOf<Int?>()
        for (number in numbers) {
            number?.let { squares = squares.plus(it * it); it }
            ?.also { println(it) }
        }
        assert( squares == listOf( 1, 4, 9) )
        assert( numbers.filterNotNull() == listOf( 1, 2, 3) )
    }
}