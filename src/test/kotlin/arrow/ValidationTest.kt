package arrow

import arrow.core.*

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

    private fun Int.validateAge(): Either<NonEmptyList<PersonError.InvalidAge>, Int> =
        if (this > 0) this.right()
        else PersonError.InvalidAge(this).nel().left()

    /**
     * This is fail-fast, not accumulate.
     */
    fun validate(person: Person): Either<NonEmptyList<PersonError>, Person> =
        if (person.name.validateName().isRight() && person.age.validateAge().isRight()) person.right()
        else PersonError.InvalidPerson(person).nel().left()
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
}