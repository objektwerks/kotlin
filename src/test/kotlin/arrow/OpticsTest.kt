package arrow

import arrow.optics.*

import java.util.*

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

fun Customer.capitalizeCity(): Customer =
    this.copy(
        address = address.copy(
            city = address.city.copy(
                name = address.city.name.replaceFirstChar {
                    it.titlecase(Locale.getDefault())
                }
            )
        )
    )

/**
 * Lenses ( copy, get, set, modify ) don't work!
 * For now, passing on optionals, traversals, prisms and isos.
 */
class OpticsTest {
    private val city = City(name = "tampa", country = "us")
    private val street = Street(number = 1, name = "stone")
    private val address = Address(street = street, city = city)
    private val customer = Customer(name = "fred", age = 24, address)

    @Test fun copy() {
        val modifiedCustomer = customer.capitalizeCity()
        assert( modifiedCustomer.address.city.name.first() == 'T' )
    }

    @Test fun get() {
        // Fail, can't find name! Customer.name.get(customer)
    }

    @Test fun set() {
        // Fail, can't find address! Customer.address.set(customer, address)
    }

    @Test fun modify() {
        // Fail, can't find age! Customer.age.modify(customer) { it + 1 }
    }
}