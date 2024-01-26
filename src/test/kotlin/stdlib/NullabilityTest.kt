package stdlib

import kotlin.test.assertFailsWith

import org.junit.Test

@Suppress("KotlinConstantConditions")
class NullabilityTest {
    @Test fun nullable() {
        val text: String? = null
        assert( text.isNullOrEmpty() )
        assert( (text?.length ?: -1) == -1 )
        assertFailsWith<NullPointerException> {
            text!!.length
        }
    }

    @Test fun notNullable() {
        val value = "value"
        assert( value.isNotEmpty() )
    }

    @Test fun nullableList() {
        val numbers = listOf(1, 2, 3, null)
        val squares = mutableListOf<Int?>()
        for (number in numbers) {
            number?.let { squares.add(it * it) }
        }
        assert( numbers.filterNotNull() == listOf( 1, 2, 3) )
        assert( squares == listOf( 1, 4, 9) )
    }
}