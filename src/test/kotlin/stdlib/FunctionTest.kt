package stdlib

import org.junit.Test

class FunctionTest {
    val sum = { x: Int, y: Int -> x + y }
    val multiply = { x: Int, y: Int -> x * y }

    @Test fun function() {
        fun square(n: Int): Int = n * n
        assert(square(2) == 4)

        fun <T> toSingleton(item: T): List<T> = listOf(item)
        assert(toSingleton(3) == listOf(3))
    }

    @Test fun higerOrder() {
        fun total(x: Int, y: Int, f: (Int, Int) -> Int): Int = f(x, y)
        assert(total(3, 3, sum) == 6)
        assert(total(3, 3, multiply) == 9)
    }

    @Test fun anonymous() {
        assert(sum(3, 3) == 6)
        assert(multiply(3, 3) == 9)
    }

    @Test fun infix() {
        class Numbers {
            infix fun cube(n: Int): Int = n * n * n
        }
        val numbers = Numbers()
        assert(numbers cube 3 == 27)
    }

    @Test fun closure() {
        var closure = 0
        listOf(1, 2, 3).filter { it > 0 }.forEach {
            closure += it
        }
        assert(closure == 6)
    }

    @Test fun varargs() {
        fun <T> toList(vararg ts: T): List<T> {
            val result = ArrayList<T>()
            for (t in ts) result.add(t)
            return result
        }
        assert(toList(1, 2, 3) == listOf(1, 2, 3))
    }

    @Test fun extend() {
        fun String.addBang(): String = "$this!"
        assert("hello".addBang() == "hello!")
    }

    @Test fun tailrec() {
        tailrec fun factorial(n: Int, acc: Int = 1): Int =
            if (n == 1) acc
            else factorial(n - 1, acc * n)
        assert(factorial(9) == 362880)
    }
}