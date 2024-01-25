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

        assert( "2010-06-01T22:19:44.475Z".toInstant().toOption().isSome() )
        "2010-06-01T22:19:44".toLocalDateTime()
        "2010-06-01".toLocalDate()
        "12:01:03".toLocalTime()
        "12:0:03.999".toLocalTime()
    }
}