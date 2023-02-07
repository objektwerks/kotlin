package stdlib

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Test

class FlowTest {
    @Test
    fun flow() {
        fun source(): Flow<Int> = kotlinx.coroutines.flow.flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }

        runBlocking {
            var sink = 0
            launch {
                for (j in 1..3) {
                    delay(100)
                }
            }
            source().collect { value -> sink += value }
            assert( sink == 6 )
        }
    }

    @Test
    fun channel() {
        runBlocking {
            var sink = 0
            val channel = Channel<Int>()
            launch {
                for (i in 1..3) channel.send(i)
            }
            repeat(3) { sink += channel.receive() }
            assert( sink == 6 )
        }
    }
}