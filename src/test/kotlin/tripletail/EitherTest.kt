package tripletail

import arrow.core.*
import arrow.core.Either.*

import kotlin.test.Test

class ToIntException(message: String) : Exception(message)

class EitherTest {
    private fun toInt(value: String): Either<ToIntException, Int> =
        when ( val integer = value.toIntOrNull() ) {
            null -> Left(ToIntException(value))
            else -> Right(integer)
        }

    @Test fun either() {
        val list = listOf("1", "2", "3", "four")

        val rights = list.map { toInt(it) }.filter { it.isRight() }
        assert(rights == listOf(Right(1), Right(2), Right(3)))

        val ints = list.map { toInt(it) }.filter { it.isRight() }.map { it.getOrElse { 0 } }
        assert( ints == listOf(1, 2, 3) )

        val sum = ints.sum()
        assert( sum == 6 )
    }
}