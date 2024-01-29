package concurrency

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.Callable

class FileLineCountTask(private val file: String) : Callable<Int> {
    private fun fileLineCount(file: String): Int {
        return try {
            Files.readAllLines( Paths.get(file) ).size
        } catch (ioe: IOException) {
            -1
        }
    }

    override fun call(): Int {
        return fileLineCount(file)
    }
}