package tripletail.stdlib

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

        val porsche = Porsche()
        assert( DerivedPorsche(porsche).race() == "prrrr" )
    }
}