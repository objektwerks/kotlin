package stdlib

import org.junit.Test

class TypeTest {
    @Test fun generic() {
        class Box<T>(t: T) {
            val value = t
        }
        assert( Box(3).value == 3 )

        fun <T> singletonList(item: T): List<T> = listOf(item)
        assert( singletonList(3) == listOf(3) )
    }

    @Test fun outin() {
        class Producer<out T>(private val t: T) {
            fun get(): T = t
        }
        class Consumer<in T> {
            fun toString(value: T): String = value.toString()
        }

        val source = Producer(3).get()
        val sink = Consumer<Int>().toString(source)
        assert(source == 3 && sink == "3")
    }
}