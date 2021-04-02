package tripletail

import arrow.core.*

import org.junit.Test

data class Person(val name: String, val age: Int)

sealed class PersonError {
    data class InvalidName(val field: String) : PersonError()
    data class InvalidAge(val field: Int) : PersonError()
}

object PersonValidator {
    @JvmStatic fun String.validatedName(): Validated<Nel<PersonError>, String> =
        if (this.isNotEmpty()) this.valid()
        else PersonError.InvalidName(this).nel().invalid()

    @JvmStatic fun Int.validatedAge(): Validated<Nel<PersonError>, Int> =
        if (this > 0) this.valid()
        else PersonError.InvalidAge(this).nel().invalid()

    fun validate(person: Person): Validated<Nel<PersonError>, Person> =
        Validated
            .applicative<Nel<PersonError>>(NonEmptyList.fromListUnsafe(listOf<PersonError>()))
            .map(person.name.validatedName(), person.age.validatedAge()) { it ->
                Person(it.a, it.b)
            }.fix()
}

class ValidatedTest {
    @Test fun valid() {
        val person = Person("Fred Flintstone", 37)
        val result = PersonValidator.validate(person)
        assert(result.isValid)
    }

    @Test fun invalid() {
        val person = Person("", 0)
        val result = PersonValidator.validate(person)
        assert(result.isInvalid)
    }
}