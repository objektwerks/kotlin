package javafx

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import javafx.application.Application
import javafx.concurrent.Task
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.Separator
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.properties.Delegates

fun main(args: Array<String>) {
    Application.launch(ChuckNorrixFxApp::class.java, *args)
}

class ChuckNorrixFxApp : Application() {
    override fun start(primaryStage: Stage) {
        val executor = Executors.newVirtualThreadPerTaskExecutor()
        val task = ChuckNorrisFxTask()
        primaryStage.apply {
            title = "Chuck Norris Jokes"
            scene = ChuckNorrixFxView(executor, task).scene()
            maxWidth = 400.0
            maxHeight = 300.0
        }
        primaryStage.centerOnScreen()
        primaryStage.show()
    }
}

class ChuckNorrixFxView(executor: Executor, task: ChuckNorrisFxTask) {
    private var jokeProperty: String by Delegates.observable("") { _, _, newJoke ->
        webview.engine.loadContent(newJoke)
    }

    private val logo = ImageView().apply {
        image = Image(this::class.java.getResourceAsStream("/cn.jpg"))
        fitHeight = 100.0
        fitWidth = 100.0
    }

    private val webview = WebView()

    private val jokeButton = Button().apply {
        prefWidth = 80.0
        prefHeight = 30.0
        text = "New Joke"
        onAction = EventHandler { _ ->
            busyIndicator.isVisible = task.isRunning
            isDisable = task.isRunning
            executor.execute(task).run { jokeProperty = task.value }
        }
    }

    private val busyIndicator = ProgressIndicator().apply {
        prefWidth = 60.0
        prefHeight = 30.0
        progress = -1.0
        isVisible = false
    }

    private val toolbar = ToolBar().apply {
        prefHeight = 40.0
        items.addAll(logo, Separator(), jokeButton, Separator(), busyIndicator)
    }

    private val webviewPane = VBox().apply {
        spacing = 3.0
        padding = Insets(3.0)
        children.add(webview)
    }

    private fun contentPane() =
        VBox().apply {
            spacing = 6.0
            padding = Insets(6.0)
            children.addAll(toolbar, webviewPane)
        }

    fun scene() = Scene(contentPane())
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