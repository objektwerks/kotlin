package tripletail.stdlib

import kotlin.test.*

import org.junit.Test

class DataClassTest {
    @Test
    fun dataClass() {
        data class Data(val value: String)
        val data = Data("data")
        val copy = data.copy( value = "copy")
        val ( destructuredValue ) = copy
        assertEquals( copy.value, destructuredValue )
        assertNotEquals( data, copy )
    }
}