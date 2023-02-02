package swing

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

import java.awt.EventQueue
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.JToolBar

class ChuckNorrisApp : JFrame() {
    private val client = HttpClient(CIO)

    private suspend fun getJoke(): String {
        return runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString()
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
    }

    private val toolbar = JToolBar()
    private val button = JButton("GetJoke")
    private val panel = JPanel()
    private val textarea = JTextArea()

    init {
        title = "Chuck Norris Jokes"
        setSize(400, 400)
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)

        button.addActionListener {
            runBlocking {
                textarea.text = getJoke()
            }
        }
    }

    fun open() {
        isVisible = true
    }

    fun close() {
        client.close()
    }
}

fun main() {
    val app = ChuckNorrisApp()
    EventQueue.invokeLater { app.open() }
    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() = runBlocking {
            app.close()
        }
    })
}