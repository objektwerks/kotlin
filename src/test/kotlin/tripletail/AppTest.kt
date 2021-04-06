package tripletail

import tripletail.console.App
import kotlin.test.Test

class AppTest {
    @Test fun greeting() {
        assert( App.greeting().isNotEmpty() )
    }
}