package stdlib

import java.util.*

import kotlin.math.absoluteValue
import kotlin.test.fail

import org.junit.Test

class WhenTest {
    @Test fun match() {
        when (val n = Random(10).nextInt().absoluteValue) {
            0 -> fail("number equals zero!")
            else -> assert( n > 10 )
        }

        val result = when (val n = Random(10).nextInt().absoluteValue) {
            in 0..10 -> fail("number is between 0 and 10!")
            else -> n
        }
        assert( result > 10 )
    }

    @Test fun isMatch() {
        fun isTypeOf(typeOf: Any): String = when(typeOf) {
            is String -> "s"
            is Int -> "i"
            is Boolean -> "2"
            else -> ""
        }

        assert(isTypeOf("string") == "s")
        assert(isTypeOf(1) == "i")
        assert(isTypeOf(true) == "2")
        assert(isTypeOf(1.0) == "")
    }
}