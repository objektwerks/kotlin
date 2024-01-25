package immutable

import kotlinx.collections.immutable.*

import org.junit.Test

/**
 * See: https://github.com/Kotlin/kotlinx.collections.immutable
 */
class ImmutableCollectionTest {
    @Test fun list() {
        val list = persistentListOf(1, 2, 3)
        assert( list.size == 3 )
        assert( list[0] == 1 )
        assert( list.getOrElse(3) { 0 } == 0 )
    }

    @Test fun set() {
        val set = persistentSetOf(1, 2, 3)
        assert( set.size == 3 )
        assert( set.contains(1) )
    }

    @Test fun map() {
        val map = persistentMapOf(1 to 1, 2 to 2, 3 to 3)
        assert( map[1] == 1 )
        assert( map.size == 3 )
        assert( map.getOrElse(4) { 0 } == 0 )
        assert( map.getOrDefault(4, 0) == 0 )
    }

    @Test fun conversion() {
        assert( listOf(1, 2, 3).toImmutableList() == persistentListOf(1, 2, 3) )
        assert( setOf(1, 2, 3).toImmutableSet() == persistentSetOf(1, 2, 3) )
    }
}