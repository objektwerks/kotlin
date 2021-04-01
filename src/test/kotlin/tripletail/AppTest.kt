package tripletail

import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun testAppGreeting() {
        assert( App().greeting.isNotEmpty() )
    }
}