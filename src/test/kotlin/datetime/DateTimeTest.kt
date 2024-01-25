package datetime

import arrow.core.toOption
import kotlinx.datetime.*

import org.junit.Test

class DateTimeTest {
    @Test fun datetime() {
        val now = Clock.System.now()
        val local = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val date = local.date
        val time = local.time
        assert( date.toOption().isNone() )
        assert( time.toOption().isNone() )
    }

    @Test fun date() {
    }

    @Test fun time() {
    }
}