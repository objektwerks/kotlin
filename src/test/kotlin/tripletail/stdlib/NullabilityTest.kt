package tripletail.stdlib

import org.junit.Test

class NullabilityTest {
    @Test
    fun nullability() {
        var text: String? = null
        assert( text.isNullOrEmpty() )

        text = "text"
        assert( text.isNotEmpty() )
    }
}