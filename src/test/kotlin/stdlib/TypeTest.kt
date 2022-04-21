package stdlib

import org.junit.Test

class TypeTest {
    @Test fun genericClass() {
        class Box<T>(t: T) {
            val value = t
        }
        assert( Box(3).value == 3 )
    }

    @Test fun genericFunction() {
        fun <T> singletonList(item: T): List<T> = listOf(item)
        assert( singletonList(3) == listOf(3) )
    }

    @Test fun outin() {
        class Producer<out T>(private val t: T) {
            fun yield(): T = t
        }
        class Consumer<in T> {
            fun asString(value: T): String = value.toString()
        }

        val source = Producer(3).yield()
        val sink = Consumer<Int>().asString(source)
        assert(source == 3 && sink == "3")
    }
}