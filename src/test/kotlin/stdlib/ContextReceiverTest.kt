package stdlib

import org.junit.Test
import kotlin.random.Random

class ContextReceiverTest {
    class Logger(private val name: String) {
        fun log(message: String): Unit = println("$name: $message")
    }
    interface LoggerContext {
        val log: Logger
    }

    context(LoggerContext)
    private fun store(value: String): Boolean {
        val logger = Logger("test")
        logger.log("stored: $value")
        return true
    }

    @Test fun context() {
        fun test(loggingContext: LoggerContext) {
            with(loggingContext) {
                assert(store(Random.nextInt().toString()))
            }
        }
    }
}