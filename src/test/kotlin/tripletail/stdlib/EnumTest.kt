package tripletail.stdlib

import org.junit.Test

enum class Beer {
    pilsner,
    lager,
    paleAle,
    indianPaleAle,
    stout,
    wheat
}

class EnumTest {
    @Test fun enum() {
        val beers = Beer.values()
        beers.asList().forEach { beer -> assert( beers.contains( beer ) ) }
    }
}