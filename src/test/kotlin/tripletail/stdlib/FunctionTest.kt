package tripletail.stdlib

import org.junit.Test

class FunctionTest {
    @Test fun function() {
        fun square(n: Int): Int = n * n
        assert( square(2) == 4 )

        fun <T> toList(vararg ts: T): List<T> {
            val result = ArrayList<T>()
            for (t in ts) result.add(t)
            return result
        }
        assert( toList(1, 2, 3) == listOf(1, 2, 3) )

        class Numbers {
            infix fun cube(n: Int): Int = n * n * n
        }
        val numbers = Numbers()
        assert( numbers cube 3 == 27 )

        fun <T> toSingleton(item: T): List<T> = listOf(item)
        assert( toSingleton(3) == listOf(3) )

    }
}