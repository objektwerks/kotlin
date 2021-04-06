package tripletail.stdlib

import org.junit.Test

enum class Ale {
    paleAle,
    indianPaleAle,
    doubleIndianPaleAle
}

class EnumTest {
    @Test fun enum() {
        assert( Ale.paleAle.toString() == "paleAle" )
        assert( Ale.indianPaleAle.toString() == "indianPaleAle" )
        assert( Ale.doubleIndianPaleAle.toString() == "doubleIndianPaleAle" )

        val beers = Ale.values()
        assert( beers.size == 3 )
        beers.asList().forEach { beer -> assert( beers.contains( beer ) ) }
    }
}