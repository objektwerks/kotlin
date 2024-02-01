package arrow

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

import org.junit.Test

object EmptyAuthorName
data class Author internal constructor(val name: String) {
    companion object {
        operator fun invoke(name: String): Either<EmptyAuthorName, Author> = either {
            ensure(name.isNotEmpty()) { EmptyAuthorName }
            Author(name)
        }
    }
}

class SmartConstructorTest {
    @Test fun smartConstructor() {
        assert( Author.invoke("").isLeft() )
        assert( Author.invoke("Fred Flintstone").isRight() )
    }
}