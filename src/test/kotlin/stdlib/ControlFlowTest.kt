package stdlib

import java.util.*

import kotlin.math.absoluteValue
import kotlin.test.fail

import org.junit.Test

class ControlFlowTest {
    @Test fun ifElse() {
        val result = if (Random(10).nextInt() > 5) 6 else 4
        assert( result > 3 )
    }

    @Test fun forIn() {
        for (n in listOf(1, 2, 3)) { assert(n > 0) }
    }

    @Test fun forEach() {
        listOf(1, 2, 3).forEach { assert( it > 0 ) }
    }

    @Test fun range() {
        for (n in 1..3) { assert(n > 0) }
    }

    @Test fun loop() {
        var a = 3
        while (a > 0) {
            a--
        }
        assert( a == 0 )

        var b = 0
        do {
            b++
        } while (b != 3)
        assert( b == 3 )
    }

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

    @Test fun repeat() {
        var sum = 0
        repeat(3) {
            sum += 1
        }
        assert( sum == 3 )
    }
}