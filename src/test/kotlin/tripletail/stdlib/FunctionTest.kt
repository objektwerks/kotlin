package tripletail.stdlib

import org.junit.Test

class FunctionTest {
    @Test
    fun function() {
        fun square(n: Int): Int = n * n
        assert( square(2) == 4 )
    }
}