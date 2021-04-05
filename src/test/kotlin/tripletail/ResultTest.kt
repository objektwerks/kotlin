package tripletail

import java.time.Instant

import org.junit.Test

class ResultTest {
    @Test
    fun result() {
        val validDateTime = "2021-04-04T12:21:41.00Z"
        val validInstant = Instant.parse(validDateTime)

        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.getOrDefault( Instant.now() ) == validInstant
        )

        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.fold( { it }, { Instant.now() } ) == validInstant
        )

    }
}