package http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class ChuckNorrisClient {
    companion object {
        @JvmStatic fun main(args: Array<String>): Unit = ChuckNorrisClient().run()
    }

    fun run(): Unit {
        runBlocking { runJoke() }
    }

    private suspend fun runJoke() {
        val client = HttpClient(CIO)

        val joke = runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString().removeSurrounding("\"")
        }.getOrDefault("Chuck is taking a power nap. Come back later.")

        println("Joke: $joke")

        client.close()
    }
}