package stdlib

import org.junit.Test
import java.io.File

class FileTest {
    @Test fun writeReadText() {
        val path = "./build/text.txt"
        val text = "test write"
        File(path).writeText(text)
        assert( File(path).readText() == text )
    }

    @Test fun writeReadBytes() {
        val path = "./build/bytes.txt"
        val bytes = "test write".encodeToByteArray()
        File(path).writeBytes(bytes)
        assert( File(path).readBytes().decodeToString() == bytes.decodeToString() )
    }
}