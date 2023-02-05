package stdlib

import kotlin.random.Random

import org.junit.Test

class ContextReceiverTest {
    class Logger(private val name: String) {
        fun log(message: String): Unit = println("$name: $message")
    }
    interface LoggerContext {
        val logger: Logger
    }

    context(LoggerContext)
    class Store {
        fun save(value: String): Boolean {
            logger.log("*** saved: $value")
            return true
        }
    }

    @Test fun context() {
        val loggerContext = object: LoggerContext {
            override val logger: Logger = Logger("test")
        }
        with(loggerContext) {
            val store = Store()
            assert(store.save(Random.nextInt().toString()))
            assert(store.save(Random.nextInt().toString()))
            assert(store.save(Random.nextInt().toString()))
        }
    }
}