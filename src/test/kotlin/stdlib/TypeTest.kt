package stdlib

import org.junit.Test

class TypeTest {
    @Test fun genericClass() {
        class Box<T>(val item: T)

        assert( Box(1).item == 1 )
    }

    @Test fun genericFunction() {
        fun <T> singletonList(item: T): List<T> = listOf(item)

        assert( singletonList(1) == listOf(1) )
    }

    @Test fun outin() {
        class Producer<out T>(private val t: T) {
            fun source(): T = t
        }

        class Consumer<in T> {
            fun sink(value: T): String = value.toString()
        }

        val source = Producer(1).source()
        val sink = Consumer<Int>().sink(source)
        assert(source == 1 && sink == "1")
    }
}