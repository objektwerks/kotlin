package swing

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    init {
        title = "Chuck Norris Jokes"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 400)
        setLocationRelativeTo(null)
    }

    fun buildToolBar(): JToolBar {
        val toolbar = JToolBar()
        toolbar
    }

    fun buildButton(): JButton {
        val button = JButton("New Joke")
        button
    }

    fun buildPanel(): JPanel {
        val panel = JPanel()
        panel
    }

    fun buildTextArea(): JTextArea {
        val textarea = JTextArea()
        textarea
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
    EventQueue.invokeLater( Runnable { app.open() } )
    GlobalScope.launch {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                app.close()
            }
        })
    }
}