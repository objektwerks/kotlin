package tripletail.stdlib

import kotlin.test.assertNull

import org.junit.Test

import kotlin.test.assertNotNull

data class Task(val description: String, val code: String? = null)

class NullabilityTest {
    @Test fun nullability() {
        var text: String? = null
        assert( text.isNullOrEmpty() )

        text = "text"
        assert( text.isNotEmpty() )

        val task = Task("Mow yard.")
        assertNull( task.code )
        assertNotNull( task.copy( code = "completed").code )
    }
}