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
    fun String.validatedName(): Validated<Nel<PersonError>, String> =
        if (this.isNotEmpty()) this.valid()
        else PersonError.InvalidName(this).nel().invalid()

    fun Int.validatedAge(): Validated<Nel<PersonError>, Int> =
        if (this > 0) this.valid()
        else PersonError.InvalidAge(this).nel().invalid()

    fun validate(person: Person): Validated<Nel<PersonError>, Person> =
        if (person.name.validatedName().isValid && person.age.validatedAge().isValid) person.valid()
        else PersonError.InvalidPerson(person).nel().invalid()
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