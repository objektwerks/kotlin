package stdlib

import arrow.core.zip

import org.junit.Test

class CollectionTest {
    @Test fun list() {
        val list = listOf(1, 2, 3)
        assert( list[0] == 1)
        assert( list.size == 3 )
        assert( list.map { it * it } == listOf(1, 4, 9) )
        assert( list.filter { it % 2 == 0 } == listOf(2) )
        assert( list.zip( listOf(4, 5, 6) ) == listOf( Pair(1, 4), Pair(2, 5), Pair(3, 6)) )
        assert( listOf( listOf("kotlin") ).flatten() == listOf("kotlin") )
    }

    @Test fun set() {
        val set = setOf(1, 2, 3)
        assert( set.size == 3 )
        assert( set.map { it * it } == listOf(1, 4, 9) )
        assert( set.filter { it % 2 == 0 } == listOf(2) )
        assert( set.zip( listOf(4, 5, 6) ) == listOf( Pair(1, 4), Pair(2, 5), Pair(3, 6)) )
    }

    @Test fun map() {
        val map = mapOf(1 to 1, 2 to 2, 3 to 3)
        assert( map.size == 3 )
        assert( map.map { it.value * it.value } == listOf(1, 4, 9) )
        assert( map.filter { it.value % 2 == 0 } == mapOf(2 to 2) )
        assert( map.zip( mapOf(1 to 4, 2 to 5, 3 to 6) ) == mapOf(1 to Pair(1, 4), 2 to Pair(2, 5), 3 to Pair(3, 6)) )
    }
}