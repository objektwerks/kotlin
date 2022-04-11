package stdlib

import org.junit.Test

interface SportsCar {
    fun race(): String
}

class DelegationTest {
    @Test fun delegation() {
        class Porsche : SportsCar {
            override fun race(): String = "prrrr"
        }
        class DerivedPorsche(derived: SportsCar) : SportsCar by derived
        assert( DerivedPorsche( Porsche() ).race() == "prrrr" )
    }
}