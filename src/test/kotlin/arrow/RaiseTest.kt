package arrow

import arrow.core.mapOrAccumulate
import arrow.core.raise.fold
import arrow.core.raise.Raise

import kotlin.test.Test

object InvalidInt

class RaiseTest {
    private fun Raise<InvalidInt>.toInt(value: String): Int =
        when ( val integer = value.toIntOrNull() ) {
            null -> raise( InvalidInt )
            else -> integer
        }

    @Test fun raise() {
        fold(
            { toInt("1") },
            { _: InvalidInt -> 0 },
            { int: Int -> assert( int == 1 ) }
        )

        assert( listOf("1", "2", "3", "four").mapOrAccumulate { toInt(it) }.isLeft() )
        assert( listOf("1", "2", "3").mapOrAccumulate { toInt(it) }.isRight() )
    }
}