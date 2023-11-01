package concurrency

import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.StructuredTaskScope

import org.junit.Test

class StructuredConcurrencyTest {
    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun structuredConcurrencyTest() {
        val lines = StructuredTaskScope.ShutdownOnFailure().use { scope ->
            val alines = scope.fork { FileLineCountTask("./data/data.a.csv").call() }
            val blines = scope.fork { FileLineCountTask("./data/data.b.csv").call() }
            scope.join()
            scope.throwIfFailed()
            alines.get() + blines.get()
        }
        assert(lines == 540959)
    }
}