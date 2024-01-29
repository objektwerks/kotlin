package arrow

import arrow.optics.optics

import kotlin.test.Test

@optics data class Customer(val name: String, val age: Int, val address: Address) {
    companion object
}
@optics data class Address(val street: Street, val city: City) {
    companion object
}
@optics data class Street( val number: Int, val name: String) {
    companion object
}
@optics data class City(val name: String, val country: String) {
    companion object
}

class OpticsTest {
    @Test fun copy() {
        val city = City(name = "tampa", country = "US")
        val street = Street(number = 1, name = "stone")
        val address = Address(street = street, city = city)
        val customer = Customer(name = "fred", age = 24, address)
        val modifiedCustomer = customer.copy(name = "Fred")
        assert( modifiedCustomer.name.first() == 'F')
    }
}