package tripletail.stdlib

import org.junit.Test

class GenericTest {
    @Test fun generic() {
        class Box<T>(t: T) {
            val value = t
        }
        assert( Box(3).value == 3 )
    }
}