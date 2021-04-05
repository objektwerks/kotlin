package tripletail

import arrow.core.*

import kotlin.test.Test

class OptionTest {
    private fun toInt(value: String): Option<Int> = Option.fromNullable(value.toIntOrNull())

    @Test fun option() {
        val list = listOf("1", "2", "3", "four")

        assert( list.map { toInt(it) } == listOf(Some(1), Some(2), Some(3), None) )

        assert( list.flatMap { toInt(it).toList() } == listOf(1, 2, 3) )

        assert( list.map { toInt(it) }.flatMap { it.toList() } == listOf(1, 2, 3) )

        assert( list.map { toInt(it) }.filterOption() == listOf(1, 2, 3) )

        assert( list.map { toInt(it) }.flatMap { it.toList() }.sum() == 6 )

        assert( Some("test").getOrElse { "default" } == "test" )

        assert( Some(2).map { it + 1 } == Some(3) )

        assert( Some(3).filter { it > 0 }  == Some(3) )

        assert( Some(3).fold( { }, { it * 3 } ) == 9)

        val value = when(val none: Option<Int> = None) {
            is Some -> none.value
            is None -> 0
        }
        assert( value == 0 )
    }
}