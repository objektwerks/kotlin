package tripletail

import java.time.Instant

import org.junit.Test

class ResultTest {
    @Test
    fun result() {
        val validDateTime = "2021-04-04T12:21:41.00Z"
        val validInstant = Instant.parse(validDateTime)

        val instant = runCatching {
            Instant.parse(validDateTime)
        }.getOrDefault(Instant.now())
        assert(instant == validInstant)

    }
}