package stdlib

import org.junit.Test

class EnumTest {
    enum class Ale {
        paleAle,
        indianPaleAle,
        doubleIndianPaleAle
    }

    enum class RGB(val value: String) {
        red("#FF0000"),
        green("#00FF00"),
        blue("#0000FF")
    }

    @Test fun enum() {
        assert( Ale.paleAle.toString() == "paleAle" )
        assert( Ale.indianPaleAle.toString() == "indianPaleAle" )
        assert( Ale.doubleIndianPaleAle.toString() == "doubleIndianPaleAle" )

        assert( Ale.valueOf("paleAle") == Ale.paleAle )
        assert( Ale.valueOf("indianPaleAle") == Ale.indianPaleAle )
        assert( Ale.valueOf("doubleIndianPaleAle") == Ale.doubleIndianPaleAle )

        val beers = Ale.values()
        assert( beers.size == 3 )
        beers.asList().forEach { beer -> assert( beers.contains( beer ) ) }

        assert( RGB.red.value == "#FF0000" )
        assert( RGB.green.value == "#00FF00" )
        assert( RGB.blue.value == "#0000FF" )
    }
}