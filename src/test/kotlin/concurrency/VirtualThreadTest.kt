package concurrency

import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.Future

import org.junit.Test

/**
 * Virtual Threads: openjdk.org/jeps/425
 * Article: www.marcobehler.com/guides/java-project-loom
 */
class VirtualThreadTest {
    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun virtualThreadTest() {
        val tasks = ArrayList<FileLineCountTask>(2)
        tasks.add(FileLineCountTask("./data/data.a.csv"))
        tasks.add(FileLineCountTask("./data/data.b.csv"))
        var lines = 0
        Executors.newVirtualThreadPerTaskExecutor().use { executor ->
            val futures: List<Future<Int>> = executor.invokeAll(tasks)
            for (future in futures) {
                lines += future.get()
            }
        }
        assert(lines == 540959)
    }
}