package swing

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import java.awt.*

import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.WindowConstants.DISPOSE_ON_CLOSE

import kotlin.properties.Delegates

import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*

class ChuckNorrisApp {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val app = ChuckNorrisApp()
            println("*** opening app ...")
            EventQueue.invokeLater { app.open() }
            Runtime.getRuntime().addShutdownHook(object : Thread() {
                override fun run() {
                    app.close()
                    println("*** closed app.")
                }
            })
        }
    }

    private val httpClient = HttpClient(CIO)

    private suspend fun getJoke(): String {
        return runCatching {
            val json = httpClient.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString().removeSurrounding("\"")
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
    }

    private val dispatcher = Dispatchers.IO

    private fun runAsyncGetJoke() =
        runBlocking(dispatcher) {
            async { jokeProperty = getJoke() }.await()
        }

    private var jokeProperty: String by Delegates.observable("")
    { _, _, newJoke ->
        EventQueue.invokeLater { textarea.text = newJoke }
    }

    private val logo = JLabel( ImageIcon( ImageIO.read( this::class.java.getResourceAsStream("/cn.jpg") ) ) )
    private val button = JButton()
    private val toolbar = JToolBar()
    private val textarea = JTextArea()
    private val panel = JPanel()
    private val frame = JFrame()

    init {
        with(button) {
            text = "New Joke"
            addActionListener {
                frame.cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)
                runAsyncGetJoke()
                frame.cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
            }
        }

        with(toolbar) {
            margin = Insets(6, 6, 6, 6)
            add(button)
        }

        with(textarea) {
            border = BorderFactory.createLineBorder(Color.lightGray, 3)
            preferredSize = Dimension(360, 120)
            background = Color.lightGray
            lineWrap = true
            wrapStyleWord = true
            isEditable = false
        }

        logo.preferredSize = Dimension(360, 280)

        with(panel) {
            border = BorderFactory.createLineBorder(Color.lightGray, 3)
            layout = BorderLayout()
            add(logo, BorderLayout.NORTH)
            add(textarea, BorderLayout.CENTER)
        }

        with(frame) {
            title = "Chuck Norris Jokes"
            setSize(400, 400)
            defaultCloseOperation = DISPOSE_ON_CLOSE
            setLocationRelativeTo(null)
            layout = BorderLayout()
            contentPane.add(toolbar, BorderLayout.NORTH)
            contentPane.add(panel, BorderLayout.CENTER)
            pack()
        }
    }

    fun open() {
        frame.isVisible = true
    }

    fun close() = httpClient.close()
}