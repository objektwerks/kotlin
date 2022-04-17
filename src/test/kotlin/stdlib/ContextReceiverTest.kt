package stdlib

import org.junit.Test
import kotlin.random.Random

class ContextReceiverTest {
    class Logger(private val name: String) {
        fun log(message: String): Unit = println("$name: $message")
    }
    interface LoggerContext {
        val logger: Logger
    }

    context(LoggerContext)
    private fun store(value: String): Boolean {
        logger.log("stored: $value")
        return true
    }

    @Test fun context() {
        val logger = Logger("test")
        val context = object: LoggerContext {
            override val logger: Logger = logger
        }
        with(context) {
            assert(store(Random.nextInt().toString()))
            assert(store(Random.nextInt().toString()))
            assert(store(Random.nextInt().toString()))
        }
    }
}