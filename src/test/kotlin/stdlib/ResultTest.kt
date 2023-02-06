package stdlib

import java.time.Instant

import org.junit.Test

/**
 * Result is a controversial type:
 * 1. https://www.droidcon.com/2022/04/06/resilient-use-cases-with-kotlin-result-coroutines-and-annotations/
 */
class ResultTest {
    private val validDateTime = "2021-02-04T12:21:41.00Z"
    private val validInstant = Instant.parse(validDateTime)

    @Test fun success() {
        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.getOrDefault { Instant.now() } == validInstant
        )
    }

    @Test fun failure() {
        assert(
            runCatching {
                Instant.parse("invalid date time")
            }.getOrDefault { Instant.now() } != validInstant
        )
    }

    @Test fun map() {
        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.map { it.toEpochMilli() }
             .getOrDefault { 0 } != 0
        )
    }

    @Test fun fold() {
        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.fold( { it }, { Instant.now() } ) == validInstant
        )
    }

    @Test fun recover() {
        assert(
            runCatching {
                Instant.parse(validDateTime)
            }.recover { Instant.now() }
             .getOrDefault { 0 } == validInstant
        )
    }
}