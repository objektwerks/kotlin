package tripletail

import org.junit.Test

class Person(name: String, age: Int)

sealed class PersonError {
    data class InvalidName(val field: String) : PersonError()
    data class InvalidAge(val field: Int) : PersonError()
}

class ValidatedTest {
    @Test fun validate() {

    }
}