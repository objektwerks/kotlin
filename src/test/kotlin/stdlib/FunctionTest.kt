package stdlib

import org.junit.Test

class FunctionTest {
    val sum = { x: Int, y: Int -> x + y }
    val multiply: (Int, Int) -> Int = { x: Int, y: Int -> x * y }

    @Test fun function() {
        fun square(n: Int): Int = n * n
        assert(square(2) == 4)

        fun <T> asSingletonList(item: T): List<T> = listOf(item)
        assert(asSingletonList(3) == listOf(3))
    }

    @Test fun higherOrder() {
        fun total(x: Int, y: Int, f: (Int, Int) -> Int): Int = f(x, y)
        assert(total(3, 3, sum) == 6)
        assert(total(3, 3, multiply) == 9)
    }

    @Test fun lambda() {
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

    @Test fun operator() {
        operator fun Int.times(value: String) = value.repeat(this)
        assert(2 * "m" == "mm")
    }

    @Test fun closure() {
        var closeOverValue = 0
        listOf(1, 2, 3).filter { it > 0 }.forEach {
            closeOverValue += it
        }
        assert(closeOverValue == 6)
    }

    @Test fun currying() {
        val curriedAdd: (Int) -> (Int) -> Int = { x: Int ->
            { y: Int -> x + y }
        }
        assert( curriedAdd(3)(6) == 9 )
    }

    @Test fun defaultValue() {
        fun divide(x: Int, y: Int = 1): Int = x / y
        assert(divide(4) == 4)
        assert(divide(4, 2) == 2)
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

    @Test fun letScope() { // it scope
        val list = listOf(1, 2, 3)
        list.map { it * it }.filter { it > 4 }.let { assert( it == listOf(9) ) }
    }

    @Test fun alsoScope() { // it scope
        val list = mutableListOf(1, 2, 3)
        list.also {
            it.add(4)
            assert( it == listOf(1, 2, 3, 4) )
        }.also {
            it.remove(4)
            assert( it == listOf(1, 2, 3) )
        }
    }

    @Test fun withScope() { // this scope
        val list = mutableListOf(1, 2, 3)
        with(list) {
            add(4)
            assert( this == listOf(1, 2, 3, 4) )
        }
    }

    @Test fun runScope() { // this scope
        class Service(val host: String, val port: Int) {
            fun query(): String = "hello!"
        }
        val service = Service("github.com", 80)
        service.run {
            assert( host == "github.com" )
            assert( port == 80 )
            assert( query() == "hello!" )
        }
    }

    @Test fun applyScope() { // this scope
        class Dog(var kind: String, var age: Int = 1, var name: String = "")
        val dog = Dog(kind = "Bulldog").apply {
            age = 1
            name = "Trouble"
        }
        dog.run {
            assert( kind == "Bulldog")
            assert( age == 1 )
            assert( name == "Trouble" )
        }
    }
}