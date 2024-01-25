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
        assert( mapOf(1 to 1, 2 to 2, 3 to 3).toImmutableMap() == persistentMapOf(1 to 1, 2 to 2, 3 to 3) )
    }

    @Test fun mutate() {
        val list = persistentListOf(1, 2, 3)
        val mutatedList = list.mutate { mutableList -> mutableList.add(4) }
        assert( mutatedList == persistentListOf(1, 2, 3, 4) )

        val set = persistentSetOf(1, 2, 3)
        val mutatedSet = set.mutate { mutableSet -> mutableSet.add(4) }
        assert( mutatedSet == persistentSetOf(1, 2, 3, 4) )
    }
}