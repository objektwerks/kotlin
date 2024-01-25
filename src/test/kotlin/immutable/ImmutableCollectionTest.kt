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
    }
}