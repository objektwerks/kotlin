package arrow

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate

import org.junit.Test

sealed class AuthorError {
    data object InvalidName : AuthorError()
    data object InvalidAge : AuthorError()
}

/**
 * This is fail-fast, not accumulate. zipOrAccumulate does not work!
 */
data class Author internal constructor(val name: String, val age: Int) {
    companion object {
        operator fun invoke(name: String, age: Int): Either<NonEmptyList<AuthorError>, Author> = either {
            zipOrAccumulate(
                { ensure(name.isNotEmpty()) { AuthorError.InvalidName } },
                { ensure(age > 0 ) { AuthorError.InvalidAge } }
            ) { _, _ -> Author(name, age) }

        }
    }
}

class SmartConstructorTest {
    @Test fun smartConstructor() {
        assert( Author.invoke("", 0).isLeft() )
        assert( Author.invoke("Fred Flintstone", 24).isRight() )
    }
}