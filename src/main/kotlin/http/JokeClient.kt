package http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

suspend fun main() {
    val client = HttpClient(CIO)
    val response = client.get("https://api.chucknorris.io/jokes/random")
    val json = response.bodyAsText()
    val jsonElement = Json.parseToJsonElement(json)
    val joke = jsonElement.jsonObject["value"]
    println(jsonElement)
    println(joke)
    client.close()
}