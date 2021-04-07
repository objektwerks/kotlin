package tripletail.stdlib

import org.junit.Test

class NullabilityTest {
    @Test
    fun nullability() {
        val text: String? = null
        assert( text.isNullOrEmpty() )
    }
}