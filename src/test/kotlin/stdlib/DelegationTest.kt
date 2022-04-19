package stdlib

import org.junit.Test

class DelegationTest {
    interface SportsCar {
        fun race(): String
    }

    class Porsche : SportsCar {
        override fun race(): String = "prrrr"
    }

    class DerivedPorsche(derived: SportsCar) : SportsCar by derived

    @Test fun delegation() {
        assert( DerivedPorsche( Porsche() ).race() == "prrrr" )
    }
}