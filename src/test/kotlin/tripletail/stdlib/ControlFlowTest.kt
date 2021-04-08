package tripletail.stdlib

import java.util.*

import org.junit.Test

class ControlFlowTest {
    @Test fun controlFlow() {
        assert( ( if ( Random(10).nextInt() > 5 ) 6 else 4 ) > 3 )
    }
}