package javafx

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

fun main(args: Array<String>) {
    Application.launch(ChuckNorrixFxApp::class.java, *args)
}

class ChuckNorrixFxApp : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Chuck Norris Jokes"
        primaryStage.scene = ChuckNorrixFxView().scene
        primaryStage.centerOnScreen()
        primaryStage.show()
    }
}

class ChuckNorrixFxView() {
    private val label = Label("Test joke!")

    val scene = Scene(label, 400.0, 300.0)
}

class ChuckNorrisFxTask() {
    private val client = HttpClient(CIO)

    private suspend fun getJoke(): String {
        return runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString()
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
    }
}