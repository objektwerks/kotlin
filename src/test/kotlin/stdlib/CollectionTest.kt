package stdlib

import arrow.core.zip

import junit.framework.TestCase.assertFalse

import kotlin.math.absoluteValue
import kotlin.random.Random

import org.junit.Test

class CollectionTest {
    @Test fun list() {
        val list = listOf(1, 2, 3)
        assert( list.size == 3 )
        assert( list[0] == 1 )
        assert( list.getOrElse(3) { 0 } == 0 )

        assert( listOf(1, 2, 3, 4) == list + 4 )
        assert( listOf(1, 2) == list - 3 )

        assert( list.count() == list.size )
        assert( list.count { it > 2 } == 1 )

        assert( list.first() == 1 )
        assert( list.last() == 3 )

        assert( list.minOrNull() == 1 )
        assert( list.maxOrNull() == 3 )

        val letters = listOf("abc", "ade")
        assert( letters.find { it.startsWith("a") } == "abc" )
        assert( letters.findLast { it.startsWith("a") } == "ade" )

        assert( list.any { it == 1 } )
        assert( list.all { it > 0 } )
        assert( list.none { it < 0 } )

        assert( list.joinToString() == "1, 2, 3" )

        assert( list.average() == 2.0 )
        assert( list.sum() == 6 )
        assert( list.sumOf { it * it } == 14 )

        assert( list.fold(0) { acc, i -> acc + i } == 6 )
        assert( list.reduce { acc, i -> acc + i } == 6 )

        assert( list.map { it * it } == listOf(1, 4, 9) )
        assert( list.filter { it % 2 == 0 } == listOf(2) )
        assert( list.zip( listOf(4, 5, 6) ) == listOf( Pair(1, 4), Pair(2, 5), Pair(3, 6)) )

        assert( listOf( listOf("kotlin") ).flatten() == listOf("kotlin") )

        assert( list.drop(2) == listOf(3) )
        assert( list.take(1) == listOf(1) )
        assert( list.slice(2..2) == listOf(3) )

        val (evens, odds) = list.partition { it % 2 == 0 }
        assert( evens == listOf(2) )
        assert( odds == listOf(1, 3) )

        data class Person(val name: String, val city: String = "tampa", val state: String = "fl")
        val men = listOf( Person("fred"), Person("barney") )
        assert( men.groupBy { it.city }["tampa"] == men )
        assert( men.groupBy { it.state }["fl"] == men )

        val women = listOf( Person("wilma"), Person("betty") )
        val couples = listOf(men, women)
        assert( couples.flatMap { couples }.count() == 4 )
    }

    @Test fun mutableList() {
        val list = mutableListOf(1, 2, 3)
        list.add(4)
        assert( listOf(1, 2, 3, 4) == list )
        list.remove(4)
        assert( listOf(1, 2, 3) == list )
        list.clear()
        assert( list.isEmpty() )
    }

    @Test fun sequence() {
        val lazyList = sequenceOf(1, 2, 3)
        assert( lazyList.map { it * it }.filter { it % 2 == 0 }.toList() == listOf(4) )
    }

    @Test fun array() {
        val array = arrayOf(1, 2, 3)
        assert( array.map { it * it }.filter { it % 2 == 0 } == listOf(4) )
        assert( array.contentEquals( arrayOf(1, 2, 3) ) && !array.contentEquals( arrayOf(3, 2, 1) ) )

        val arrayList = arrayListOf(1, 2, 3)
        assert( arrayList.map { it * it }.filter { it % 2 != 0 } == listOf(1, 9) )

        val intArray = intArrayOf(1, 2, 3)
        assert( intArray.map { it * it * it }.filter { it % 2 == 0 } == listOf(8) )
    }

    @Test fun set() {
        val set = setOf(1, 2, 3)
        assert( set.size == 3 )
        assert( set.contains(1) )

        assert( setOf(1, 2, 3, 4) == set + 4 )
        assert( setOf(1, 2) == set - 3 )

        assert( set.map { it * it } == listOf(1, 4, 9) )
        assert( set.filter { it % 2 == 0 } == listOf(2) )
        assert( set.zip( listOf(4, 5, 6) ) == listOf( Pair(1, 4), Pair(2, 5), Pair(3, 6)) )

        assert( set union setOf(4, 5, 6) == setOf(1, 2, 3, 4, 5, 6) )
        assert( setOf(2, 4, 6) intersect setOf(4, 6, 8) == setOf(4, 6) )
    }

    @Test fun mutableSet() {
        val set = mutableSetOf(1, 2, 3)
        set.add(4)
        assert( setOf(1, 2, 3, 4) == set )
        set.remove(4)
        assert( setOf(1, 2, 3) == set )
        set.clear()
        assert( set.isEmpty() )
    }

    @Test fun sortedSet() {
        val set = sortedSetOf(3, 2, 1)
        assert( set.toList() == listOf(1, 2, 3) )
    }

    @Test fun map() {
        val map = mapOf(1 to 1, 2 to 2, 3 to 3)
        assert( map[1] == 1 )
        assert( map.size == 3 )
        assert( map.getOrElse(4) { 0 } == 0 )
        assert( map.getOrDefault(4, 0) == 0 )

        assert( mapOf(1 to 1, 2 to 2) == mapOf(1 to 1 ) + (2 to 2) )
        assert( mapOf(1 to 1) == mapOf(1 to 1, 2 to 2) - 2 )

        map.forEach { (k, v) -> assert( k == v ) }
        assert( map.map { it.value * it.value } == listOf(1, 4, 9) )
        assert( map.filter { it.value % 2 == 0 } == mapOf(2 to 2) )
        assert( map.zip( mapOf(1 to 4, 2 to 5, 3 to 6) ) == mapOf(1 to Pair(1, 4), 2 to Pair(2, 5), 3 to Pair(3, 6)) )
    }

    @Test fun mutableMap() {
        val map = mutableMapOf(1 to 1, 2 to 2, 3 to 3)
        map[4] = 4
        assert( map == mutableMapOf(1 to 1, 2 to 2, 3 to 3, 4 to 4) )
        map.remove(4)
        assert( map == mutableMapOf(1 to 1, 2 to 2, 3 to 3) )
        map.clear()
        assert( map.isEmpty() )
    }

    @Test fun sortedMap() {
        val map = sortedMapOf(3 to 3, 2 to 2, 1 to 1)
        assert( map == mapOf(1 to 1, 2 to 2, 3 to 3) )
    }

    @Test fun sorting() {
        assert( listOf(1, 2, 3).reversed() == listOf(3, 2, 1) )
        assert( listOf(3, 2, 1).sorted() == listOf(1, 2, 3) )

        assert( setOf(1, 2, 3).reversed() == listOf(3, 2, 1) )
        assert( setOf(3, 2, 1).sorted() == listOf(1, 2, 3) )

        assert( listOf("c", "b", "a").sorted() == listOf("a", "b", "c") )
        assert( listOf("a", "b", "c").sortedDescending() == listOf("c", "b", "a") )

        assert( listOf("three", "four", "two").sortedBy { it.length } == listOf("two", "four", "three") )
        assert( listOf("two", "four", "three").sortedByDescending { it.length } == listOf("three", "four", "two") )

        assert( listOf("aaa", "bb", "c").sortedWith( compareBy { it.length } ) == listOf("c", "bb", "aaa") )
    }

    @Test fun equality() {
        val authors = setOf("Shakespeare", "Hemingway", "Twain")
        val writers = setOf("Twain", "Shakespeare", "Hemingway")
        assert( authors == writers )
        assertFalse( authors === writers )
    }

    @Test fun takeIfUnless() {
        val number = Random.nextInt(10).absoluteValue
        val evenOrNull = number.takeIf { it % 2 == 0 } ?: 0
        assert( evenOrNull >= 0 )
        val oddOrNull = number.takeUnless { it % 2 == 0 } ?: 0
        assert( oddOrNull >= 0 )
    }
}