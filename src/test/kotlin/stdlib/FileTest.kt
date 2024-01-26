package stdlib

import java.io.File
import javax.imageio.ImageIO

import org.junit.Test

class FileTest {
    @Test fun writeReadBytes() {
        val path = "./build/bytes.txt"
        val bytes = "test write".encodeToByteArray()
        File(path).writeBytes(bytes)
        assert(
            File(path)
                .readBytes()
                .decodeToString() == bytes.decodeToString()
        )
    }

    @Test fun writeReadText() {
        val path = "./build/text.txt"
        val text = "test write"
        File(path).writeText(text)
        assert( File(path).readText() == text )
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
        val text = this::class.java.getResource("/config.yaml")?.readText()
        assert( text != null )
    }

    @Test fun resourceAsStream() {
        val lines = this::class.java.getResourceAsStream("/config.yaml")
            ?.bufferedReader()
            ?.readLines()
        assert( lines?.count() == 2 )
    }

    @Test fun use() {
        File("./ddl.sql").inputStream().use {
            assert( it.readAllBytes().isNotEmpty() )
        }
    }

    @Test fun imageIO() {
        val image = ImageIO.read(this::class.java.getResourceAsStream("/cn.jpg"))
        assert( image.width > 0 )
        assert( image.height > 0 )
    }
}