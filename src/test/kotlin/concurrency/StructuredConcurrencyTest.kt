package concurrency

import jdk.incubator.concurrent.StructuredTaskScope.ShutdownOnFailure
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future

import org.junit.Test

/**
 * Run > Edit Configurations > StructuredConcurrencyTest
 * --enable-preview --add-modules jdk.incubator.concurrent
 * Structured Concurrency: openjdk.org/jeps/428
 */
class StructuredConcurrencyTest {
    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun structuredConcurrencyTest() {
        var lines: Int
        ShutdownOnFailure().use { scope ->
            val alines: Future<Int> = scope.fork { FileLineCountTask("./data/data.a.csv").call() }
            val blines: Future<Int> = scope.fork { FileLineCountTask("./data/data.b.csv").call() }
            scope.join()
            scope.throwIfFailed()
            lines = alines.resultNow() + blines.resultNow()
        }
        assert(lines == 540959)
    }
}