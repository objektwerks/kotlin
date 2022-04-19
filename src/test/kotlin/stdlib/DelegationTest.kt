package stdlib

import org.junit.Test

class DelegationTest {
    interface Car {
        fun race(): String
    }

    class SportsCar : Car {
        override fun race(): String = "prrrr"
    }

    class Porsche : Car by SportsCar()

    @Test fun delegation() {
        assert( Porsche().race() == "prrrr" )
    }
}