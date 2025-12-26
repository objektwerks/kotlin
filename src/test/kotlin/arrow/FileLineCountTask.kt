package arrow

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.Callable

internal class FileLineCountTask(var file: String) : Callable<Int?> {
    fun fileLineCount(file: String): Int {
        try {
            val path = Paths.get(file)
            return Files.readAllLines(path).size
        } catch (ioe: IOException) {
            return -1
        }
    }

    override fun call(): Int {
        return fileLineCount(file)
    }
}