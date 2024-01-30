package arrow

import arrow.fx.coroutines.Schedule

import kotlin.test.Test

class ResilenceTest {
    @Test fun recur() {
        var counter = 0
        Schedule.recurs<Unit>(3).repeat { // 1 + 3
            counter++
        }
        assert( counter == 4)
    }
}