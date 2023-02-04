package javafx

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import javafx.application.Application
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.embed.swing.SwingFXUtils
import javafx.event.EventHandler
import javafx.geometry.Insets
import javax.imageio.ImageIO
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.Separator
import javafx.scene.control.ToolBar
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.stage.Stage

import java.util.concurrent.Executor
import java.util.concurrent.Executors

import kotlin.properties.Delegates

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject


fun main(args: Array<String>) {
    Application.launch(ChuckNorrixFxApp::class.java, *args)
}

class ChuckNorrixFxApp : Application() {
    private val executor = Executors.newVirtualThreadPerTaskExecutor()

    override fun start(primaryStage: Stage) {
        primaryStage.apply {
            title = "Chuck Norris Jokes"
            scene = ChuckNorrixFxView(executor).scene()
            maxWidth = 360.0
            maxHeight = 360.0
        }
        primaryStage.centerOnScreen()
        primaryStage.show()
    }
}

class ChuckNorrixFxView(executor: Executor) {
    private val client = HttpClient(CIO)

    private var htmlProperty: String by Delegates.observable("") { _, _, newJoke ->
        Platform.runLater { webview.engine.loadContent(newJoke) }
    }

    private val bufferedImage = ImageIO.read(this::class.java.getResourceAsStream("/cn.jpg"))
    private val logo = ImageView().apply {
        image = SwingFXUtils.toFXImage(bufferedImage, null )
        fitHeight = 160.0
        fitWidth = 160.0
        isPreserveRatio = true
        isSmooth = true
    }

    private val webview = WebView()

    private val jokeButton = Button().apply {
        prefWidth = 80.0
        prefHeight = 30.0
        text = "New Joke"
        onAction = EventHandler { _ ->
            val task = ChuckNorrisFxTask(client)
            busyIndicator.progressProperty().bind(task.progressProperty())
            busyIndicator.visibleProperty().bind(task.runningProperty())
            disableProperty().bind(task.runningProperty())
            task.valueProperty().addListener { _, _, newValue ->
                htmlProperty = newValue
            }
            executor.execute(task)
        }
    }

    private val busyIndicator = ProgressIndicator().apply {
        prefWidth = 60.0
        prefHeight = 30.0
        isVisible = false
    }

    private val toolbar = ToolBar().apply {
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

class ChuckNorrisFxTask(private val client: HttpClient) : Task<String>() {
    private suspend fun getJoke(): String {
        return runCatching {
            val text = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(text)
            val value = jsonElement.jsonObject["value"].toString().removeSurrounding("\"")
            "<p>$value</p>"
        }.getOrDefault("<p>Chuck is taking a power nap. Come back later.</p>")
    }

    override fun call(): String = runBlocking { getJoke() }
}