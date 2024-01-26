package stdlib

import java.time.Instant

import org.junit.Test
import org.junit.Assert.fail

/**
 * Result is a controversial type:
 * 1. https://elizarov.medium.com/kotlin-and-exceptions-8062f589d07
 * 2. https://www.droidcon.com/2022/04/06/resilient-use-cases-with-kotlin-result-coroutines-and-annotations/
 */
class ResultTest {
    private val validDateTime = "2021-02-04T12:21:41.00Z"
    private val invalidDateTime = "invalid date time"
    private val validInstant = Instant.parse(validDateTime)

    @Test fun on() {
        runCatching {
            Instant.parse(validDateTime)
        }.onFailure { fail(invalidDateTime) }
         .onSuccess { assert( true ) }
    }

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
                Instant.parse(invalidDateTime)
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
                Instant.parse(invalidDateTime)
            }.recover { Instant.now() }
             .getOrDefault { 0 } != 0
        )
    }
}