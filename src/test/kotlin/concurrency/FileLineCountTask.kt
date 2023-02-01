package concurrency

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.Callable

class FileLineCountTask(var file: String) : Callable<Int> {
    fun fileLineCount(file: String?): Int {
        return try {
            val path = Paths.get(file)
            Files.readAllLines(path).size
        } catch (ioe: IOException) {
            -1
        }
    }

    override fun call(): Int {
        return fileLineCount(file)
    }
}