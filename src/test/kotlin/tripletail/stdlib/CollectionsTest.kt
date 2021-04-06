package tripletail.stdlib

import org.junit.Test

class CollectionsTest {
    @Test
    fun list() {
        val list = listOf(1, 2, 3)
        assert( list.size == 3 )
        assert( list.map { it * it } == listOf(1, 4, 9) )
        assert( list.filter { it % 2 == 0 } == listOf(2) )
    }

    @Test
    fun set() {
        val set = setOf(1, 2, 3)
        assert( set.size == 3 )
        assert( set.map { it * it } == listOf(1, 4, 9) )
        assert( set.filter { it % 2 == 0 } == listOf(2) )
    }

    @Test
    fun map() {
        val map = mapOf(1 to 1, 2 to 2, 3 to 3)
        assert( map.size == 3 )
        assert( map.map { it.value * it.value } == listOf(1, 4, 9) )
        assert( map.filter { it.value % 2 == 0 } == mapOf(2 to 2) )
    }
}