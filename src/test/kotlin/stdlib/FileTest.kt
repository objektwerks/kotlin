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

    @Test fun useLines() {
        File("./LICENSE").useLines { assert( it.count() == 48 ) }
    }

    @Test fun readLines() {
        assert( File("./LICENSE").readLines().count() == 48 )
    }

    @Test fun resource() {
        assert( this::class.java.getResource("/config.yaml").readText().isNotEmpty() )
    }
}