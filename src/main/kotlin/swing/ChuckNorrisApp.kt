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
import javax.swing.JFrame

class ChuckNorrisApp : JFrame() {
    private val client = HttpClient(CIO)

    init {
        title = "Chuck Norris Jokes"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 400)
        setLocationRelativeTo(null)
    }

    suspend fun getJoke(): String {
        return runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString()
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
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