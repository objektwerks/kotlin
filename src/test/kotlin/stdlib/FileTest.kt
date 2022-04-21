package stdlib

import org.junit.Test
import java.io.File

class FileTest {
    @Test fun writeReadText() {
        val path = "./build/test.txt"
        val text = "test write"
        File(path).writeText(text)
        assert( File(path).readText() == text )
    }
}