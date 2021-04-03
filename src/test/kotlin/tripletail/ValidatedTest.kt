package tripletail

import arrow.core.*
import arrow.typeclasses.*

import org.junit.Test

data class Person(val name: String, val age: Int)

sealed class PersonError {
    data class InvalidName(val field: String) : PersonError()
    data class InvalidAge(val field: Int) : PersonError()
}

object PersonValidator {
    private fun String.validatedName(): Validated<Nel<PersonError>, String> =
        if (this.isNotEmpty()) this.valid()
        else PersonError.InvalidName(this).nel().invalid()

    private fun Int.validatedAge(): Validated<Nel<PersonError>, Int> =
        if (this > 0) this.valid()
        else PersonError.InvalidAge(this).nel().invalid()

    /*
        Validated
            .applicative<Nel<PersonError>>(Semigroup.nonEmptyList<PersonError>())
            .map(person.name.validatedName(), person.age.validatedAge()) { it ->
                Person(it.a, it.b)
            }.fix()
    */
    fun validate(person: Person): Validated<Nel<PersonError>, Person> {
        val name = person.name.validatedName()
        val age = person.age.validatedAge()
        val list = Semigroup.nonEmptyList<PersonError>()
        return Validated<Nel<PersonError>, Person>
            .combineK(list, name)
            .combineK(list, age)
    }
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