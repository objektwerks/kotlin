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
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                    else it.toString()
                }
            )
        )
    )

/* Optics ( copy, get, set, modify ) don't work at this time!
fun Customer.capitalizeCountryModify(): Customer = this.address.city.name.modify(this) { it.capitalize() }
fun Customer.capitalizeCountryCopy(): Customer = this.copy { this.address.city.name transform { it.capitalize() } }
 */

class OpticsTest {
    private val city = City(name = "tampa", country = "us")
    private val street = Street(number = 1, name = "stone")
    private val address = Address(street = street, city = city)
    private val customer = Customer(name = "fred", age = 24, address)

    @Test fun optics() {
        val modifiedCustomer = customer.capitalizeCity() // Substitute with standard copy!
        assert( modifiedCustomer.address.city.name.first() == 'T')
    }
}