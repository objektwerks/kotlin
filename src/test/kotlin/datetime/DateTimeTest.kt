package datetime

import arrow.core.toOption

import kotlinx.datetime.*

import org.junit.Test

class DateTimeTest {
    @Test fun datetime() {
        val now = Clock.System.now()
        val local = now.toLocalDateTime(TimeZone.currentSystemDefault())

        val nowAsString = now.toString()
        val parsedNow = Instant.parse(nowAsString)
        assert( parsedNow.toOption().isSome() )

        val date = local.date
        val time = local.time
        assert( date.toOption().isSome() )
        assert( time.toOption().isSome() )

        val knownDate = LocalDate(year = 2020, monthNumber = 1, dayOfMonth = 1)
        assert( knownDate.toOption().isSome() )

        val knownTime = LocalTime(hour = 23, minute = 59, second = 59)
        assert( knownTime.toOption().isSome() )

        assert( "2020-12-01T22:19:44.475Z".toInstant().toOption().isSome() )
        assert( "2020-12-01T22:19:44".toLocalDateTime().toOption().isSome() )
        assert( "2020-12-01".toLocalDate().toOption().isSome() )
        assert( "12:01:03".toLocalTime().toOption().isSome() )
        assert( "12:01:03.999".toLocalTime().toOption().isSome() )

        val past = Instant.parse("2020-01-01T00:00:00Z")
        val duration = now - past
        val future = now + duration
        assert( duration.toOption().isSome() )
        assert( future.toOption().isSome() )

        val period = past.periodUntil(Clock.System.now(), TimeZone.UTC)
        assert(period.years > 0)
        // assert(period.months > 0) // bug!
        assert(period.days > 0)
        assert(period.hours > 0)
    }
}