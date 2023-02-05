package stdlib

import java.time.Instant

import org.junit.Test

class ResultTest {
    @Test fun result() {
        val validDateTime = "2021-02-04T12:21:41.00Z"
        val validInstant = Instant.parse(validDateTime)

        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.getOrDefault { Instant.now() } == validInstant
        )

        assert(
            runCatching {
                Instant.parse("invalid date time")
            }.getOrDefault { Instant.now() } != validInstant
        )

        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.map { it.toEpochMilli() }
             .getOrDefault { 0 } != Instant.now().toEpochMilli()
        )

        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.fold( { it }, { Instant.now() } ) == validInstant
        )

        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.recover { Instant.now() }
             .getOrDefault { 0 } == validInstant
        )
    }
}