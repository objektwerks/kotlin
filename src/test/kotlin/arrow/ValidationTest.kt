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

@JvmInline value class Name(val value: String)
@JvmInline value class Age(val value: Int)

/**
 * Smart constructor pattern!
 * Validation accmulates errors!
 *
 * The constructor should be private, not internal.
 * See: https://youtrack.jetbrains.com/issue/KT-11914/Confusing-data-class-copy-with-private-constructor
 */
@Suppress("DataClassPrivateConstructor") // Revist this recent change to Kotlin.
data class Author private constructor(val name: Name, val age: Age) {
    companion object {
        operator fun invoke(name: Name, age: Age): Either<NonEmptyList<AuthorError>, Author> = either {
            zipOrAccumulate(
                { ensure(name.value.isNotEmpty()) { AuthorError.InvalidName } },
                { ensure(age.value > 0 ) { AuthorError.InvalidAge } }
            ) { _, _ -> Author(name, age) }

        }
    }
}

class ValidationTest {
    @Test fun valid() {
        assert( Author.invoke( Name("Fred Flintstone"), Age(24) ).isRight() )
    }

    @Test fun invalid() {
        assert( Author.invoke( Name(""), Age(0) ).isLeft() )
        assert( Author.invoke( Name(""), Age(0) ).fold( { it.size }, { null }) == 2 )
    }
}