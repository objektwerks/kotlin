package swing

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*
import java.awt.*
import javax.imageio.ImageIO

import javax.swing.*
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class ChuckNorrisApp {
    private val client = HttpClient(CIO)

    private suspend fun getJoke(): String {
        return runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString()
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
    }

    private val logo = JLabel(
        ImageIcon(
            ImageIO.read(this::class.java.getResourceAsStream("/cn.jpg"))
        )
    )

    private val frame = JFrame()
    private val button = JButton()
    private val toolbar = JToolBar()
    private val textarea = JTextArea()
    private val panel = JPanel()

    init {
        frame.title = "Chuck Norris Jokes"
        frame.setSize(400, 400)
        frame.defaultCloseOperation = EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.layout = BorderLayout()

        button.text = "New Joke"
        button.addActionListener {
            frame.cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)
            val json = runBlocking { getJoke() }
            textarea.text = json.removeSurrounding("\"")
            frame.cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
        }

        toolbar.add(button)
        frame.contentPane.add(toolbar, BorderLayout.NORTH)

        textarea.border = BorderFactory.createLineBorder(Color.lightGray, 3)
        textarea.preferredSize = Dimension(360, 120)
        textarea.background = Color.lightGray
        textarea.lineWrap = true
        textarea.isEditable = false

        logo.preferredSize = Dimension(360, 280)
        logo.background = Color.lightGray

        panel.border = BorderFactory.createLineBorder(Color.lightGray, 3)
        panel.layout = BorderLayout()
        panel.add(logo, BorderLayout.NORTH)
        panel.add(textarea, BorderLayout.CENTER)

        frame.contentPane.add(panel, BorderLayout.CENTER)
        frame.pack()
    }

    fun open() {
        frame.isVisible = true
    }

    fun close() = client.close()
}

fun main() {
    val app = ChuckNorrisApp()
    EventQueue.invokeLater { app.open() }
    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() = runBlocking { app.close() }
    })
}