package immutable

import arrow.core.zip

import kotlinx.collections.immutable.*

import org.junit.Test

/**
 * See: https://github.com/Kotlin/kotlinx.collections.immutable
 */
class PersistentCollectionTest {
    @Test fun list() {
        val list = persistentListOf(1, 2, 3)
        assert( list.size == 3 )
        assert( list[0] == 1 )
        assert( list.getOrElse(3) { 0 } == 0 )

        assert( list.first() == 1 )
        assert( list.last() == 3 )

        assert( list.minOrNull() == 1 )
        assert( list.maxOrNull() == 3 )

        assert( list.average() == 2.0 )
        assert( list.sum() == 6 )

        assert( list.fold(0) { acc, i -> acc + i } == 6 )
        assert( list.reduce { acc, i -> acc + i } == 6 )

        assert( list.map { it * it } == persistentListOf(1, 4, 9) )
        assert( list.filter { it % 2 == 0 } == persistentListOf(2) )
        assert( list.zip( persistentListOf(4, 5, 6) ) == persistentListOf( Pair(1, 4), Pair(2, 5), Pair(3, 6)) )

        assert( persistentListOf( persistentListOf("kotlin") ).flatten() == persistentListOf("kotlin") )

        assert( list.drop(2) == persistentListOf(3) )
        assert( list.take(1) == persistentListOf(1) )
        assert( list.slice(2..2) == persistentListOf(3) )

        val (evens, odds) = list.partition { it % 2 == 0 }
        assert( evens == persistentListOf(2) )
        assert( odds == persistentListOf(1, 3) )

        assert( list.any { it == 1 } )
        assert( list.all { it > 0 } )
        assert( list.none { it < 0 } )

        assert( list.joinToString() == "1, 2, 3" )

        val letters = persistentListOf("abc", "ade")
        assert( letters.find { it.startsWith("a") } == "abc" )
        assert( letters.findLast { it.startsWith("a") } == "ade" )

        data class Person(val name: String, val city: String = "tampa", val state: String = "fl")
        val men = persistentListOf( Person("fred"), Person("barney") )
        assert( men.groupBy { it.city }["tampa"] == men )
        assert( men.groupBy { it.state }["fl"] == men )

        val women = persistentListOf( Person("wilma"), Person("betty") )
        val couples = persistentListOf(men, women)
        assert( couples.flatMap { couples }.count() == 4 )

        assert( list.clear().isEmpty() )
    }

    @Test fun set() {
        val set = persistentSetOf(1, 2, 3)
        assert( set.size == 3 )
        assert( set.contains(1) )

        assert( persistentSetOf(1, 2, 3, 4) == set + 4 )
        assert( persistentSetOf(1, 2) == set - 3 )

        assert( set.map { it * it } == persistentListOf(1, 4, 9) )
        assert( set.filter { it % 2 == 0 } == persistentListOf(2) )
        assert( set.zip( persistentListOf(4, 5, 6) ) == persistentListOf( Pair(1, 4), Pair(2, 5), Pair(3, 6)) )

        assert( set union persistentSetOf(4, 5, 6) == persistentSetOf(1, 2, 3, 4, 5, 6) )
        assert( persistentSetOf(2, 4, 6) intersect persistentSetOf(4, 6, 8) == persistentSetOf(4, 6) )

        assert( set.clear().isEmpty() )
    }

    @Test fun map() {
        val map = persistentMapOf(1 to 1, 2 to 2, 3 to 3)
        assert( map[1] == 1 )
        assert( map.size == 3 )
        assert( map.getOrElse(4) { 0 } == 0 )
        assert( map.getOrDefault(4, 0) == 0 )

        assert( persistentMapOf(1 to 1, 2 to 2) == persistentMapOf(1 to 1 ) + (2 to 2) )
        assert( persistentMapOf(1 to 1) == persistentMapOf(1 to 1, 2 to 2) - 2 )

        map.forEach { (k, v) -> assert( k == v ) }
        assert( map.map { it.value * it.value } == persistentListOf(1, 4, 9) )
        assert( map.filter { it.value % 2 == 0 } == persistentMapOf(2 to 2) )
        assert( map.zip( persistentMapOf(1 to 4, 2 to 5, 3 to 6) ) == persistentMapOf(1 to Pair(1, 4), 2 to Pair(2, 5), 3 to Pair(3, 6)) )
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

        val map = persistentMapOf(1 to 1, 2 to 2, 3 to 3)
        val mutatedMap = map.mutate { mutableMap -> mutableMap[4] = 4 }
        assert( mutatedMap == persistentMapOf(1 to 1, 2 to 2, 3 to 3, 4 to 4) )
    }
}