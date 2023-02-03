package javafx

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import javafx.application.Application
import javafx.concurrent.Task
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

fun main(args: Array<String>) {
    Application.launch(ChuckNorrixFxApp::class.java, *args)
}

class ChuckNorrixFxApp : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.apply {
            title = "Chuck Norris Jokes"
            scene = ChuckNorrixFxView( ChuckNorrisFxTask()).scene()
            maxWidth = 400.0
            maxHeight = 300.0
        }
        primaryStage.centerOnScreen()
        primaryStage.show()
    }
}

class ChuckNorrixFxView(task: ChuckNorrisFxTask) {
    private val logo = ImageView().apply {
        image = Image(this::class.java.getResourceAsStream("/cn.jpg"))
        fitHeight = 100.0
        fitWidth = 100.0
    }

    private val webview = WebView()

    private fun content() =
        VBox().apply {
            spacing = 6.0
            padding = Insets(6.0)
            children.add(VBox())
        }

    fun scene() = Scene(content())
}

class ChuckNorrisFxTask : Task<String>() {
    private val client = HttpClient(CIO)

    private suspend fun getJoke(): String {
        return runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString()
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
    }

    override fun call(): String = runBlocking { getJoke() }
}