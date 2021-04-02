package tripletail

import arrow.core.*

import kotlin.test.Test

class OptionTest {
    private fun toInt(value: String): Option<Int> = Option.fromNullable(value.toIntOrNull())

    @Test fun option() {
        val list = listOf("1", "2", "3", "four")

        val options = list.map { toInt(it) }
        assert( options == listOf(Some(1), Some(2), Some(3), None) )

        val ints = list.map { toInt(it) }.filterOption()
        assert( ints == listOf(1, 2, 3) )

        val sum = ints.sum()
        assert( sum == 6 )
    }
}