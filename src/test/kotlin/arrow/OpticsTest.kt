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

fun Customer.capitalizeCountry(): Customer =
    this.copy(
        address = address.copy(
            city = address.city.copy(
                country = address.city.country.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                    else it.toString()
                }
            )
        )
    )

/* Optics ( copy, get, set, modify ) don't work at this time!
fun Customer.capitalizeCountryModify(): Customer = this.address.city.country.modify(this) { it.capitalize() }
fun Customer.capitalizeCountryCopy(): Customer = this.copy { this.address.city.country transform { it.capitalize() } }
 */

class OpticsTest {
    @Test fun optics() {
        val city = City(name = "tampa", country = "US")
        val street = Street(number = 1, name = "stone")
        val address = Address(street = street, city = city)
        val customer = Customer(name = "fred", age = 24, address)
        val modifiedCustomer = customer.capitalizeCountry() // Substitute with standard copy!
        assert( modifiedCustomer.name.first() == 'F')
    }
}