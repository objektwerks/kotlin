package http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun main() {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("http://api.icndb.com/jokes/random/")
    println(response.status)
    println(response.bodyAsText())
    client.close()
}