package http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

suspend fun main() {
    val client = HttpClient(CIO)

    val joke = runCatching {
        val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
        val jsonElement = Json.parseToJsonElement(json)
        println("json: $jsonElement")
        jsonElement.jsonObject["value"].toString()
    }.getOrDefault("Chuck is taking a power nap. Come back later.")

    println("joke: $joke")

    client.close()
}