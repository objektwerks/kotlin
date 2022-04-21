package stdlib

import org.junit.Test

class GenericTest {
    @Test fun generic() {
        class Box<T>(t: T) {
            val value = t
        }
        assert( Box(3).value == 3 )

        fun <T> singletonList(item: T): List<T> = listOf(item)
        assert( singletonList(3) == listOf(3) )
    }

    @Test fun inout() {
        class Producer<out T>(private val t: T) {
            fun get(): T {
                return t
            }
        }
        assert( Producer(3).get() == 3 )

        class Consumer<in T> {
            fun toString(value: T): String = value.toString()
        }
        assert( Consumer<Int>().toString(3) == "3" )
    }
}