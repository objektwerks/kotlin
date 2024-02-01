package arrow

import arrow.core.*
import arrow.core.raise.either
import arrow.core.raise.ensure

import org.junit.Test

data class Person(val name: String, val age: Int)
sealed class PersonError {
    data class InvalidName(val name: String) : PersonError()
    data class InvalidAge(val age: Int) : PersonError()
    data class InvalidPerson(val person: Person) : PersonError()
}
object PersonValidator {
    private fun String.validateName(): Either<NonEmptyList<PersonError.InvalidName>, String> =
        if (this.isNotEmpty()) this.right()
        else PersonError.InvalidName(this).nel().left()

    private fun Int.validatedAge(): Either<NonEmptyList<PersonError.InvalidAge>, Int> =
        if (this > 0) this.right()
        else PersonError.InvalidAge(this).nel().left()

    fun validate(person: Person): Either<NonEmptyList<PersonError>, Person> =
        if (person.name.validateName().isRight() && person.age.validatedAge().isRight()) person.right()
        else PersonError.InvalidPerson(person).nel().left()
}

object EmptyAuthorName
data class Author internal constructor(val name: String) {
    companion object {
        operator fun invoke(name: String): Either<EmptyAuthorName, Author> = either {
            ensure(name.isNotEmpty()) { EmptyAuthorName }
            Author(name)
        }
    }
}

class ValidatedTest {
    @Test fun valid() {
        val person = Person("Fred Flintstone", 24)
        val result = PersonValidator.validate(person)
        assert( result.isRight() )
    }

    @Test fun invalid() {
        val person = Person("", 0)
        val result = PersonValidator.validate(person)
        assert( result.isLeft() )
    }

    @Test fun smartConstructor() {
        assert( Author.invoke("").isLeft() )
        assert( Author.invoke("Fred Flintstone").isRight() )
    }
}