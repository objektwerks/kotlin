package tripletail.stdlib

import org.junit.Test

class CollectionsTest {
    @Test
    fun list() {
        val list = listOf(1, 2, 3)
        assert( list.size == 3 )
    }

    @Test
    fun set() {
        val set = setOf(1, 2, 3)
        assert( set.size == 3 )
    }

    @Test
    fun map() {
        val map = mapOf( 1 to 1, 2 to 2, 3 to 3)
        assert( map.size == 3 )
    }
}