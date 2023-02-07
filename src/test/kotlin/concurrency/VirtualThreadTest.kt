package concurrency

import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors

import org.junit.Test

/**
 * Configure in gradle: --enable-preview --add-modules jdk.incubator.concurrent
 * Virtual Threads: openjdk.org/jeps/425
 * Article: www.marcobehler.com/guides/java-project-loom
 */
class VirtualThreadTest {
    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun virtualThreadTest() {
        val tasks = listOf(
            FileLineCountTask("./data/data.a.csv"),
            FileLineCountTask("./data/data.b.csv")
        )
        val lines = Executors.newVirtualThreadPerTaskExecutor().use { executor ->
            executor.invokeAll(tasks).sumOf { future -> future.get() }
        }
        assert(lines == 540959)
    }
}