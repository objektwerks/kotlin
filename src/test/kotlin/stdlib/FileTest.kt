package stdlib

import java.io.File

import org.junit.Test

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

    @Test fun forEachLine() {
        val buffer = mutableListOf<String>()
        File("./LICENSE").forEachLine {
            buffer.add(it)
        }
        assert( buffer.count() == 48 )
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

    @Test fun resourceAsStream() {
        assert( this::class.java.getResourceAsStream("/config.yaml").bufferedReader().readLines().count() == 2 )
    }
}