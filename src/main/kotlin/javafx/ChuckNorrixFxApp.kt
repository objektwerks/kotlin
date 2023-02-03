package javafx

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import javafx.application.Application
import javafx.application.Platform
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

import kotlin.properties.Delegates

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
            scene = ChuckNorrixFxView( ChuckNorrisFxTask() ).scene()
            maxWidth = 400.0
            maxHeight = 300.0
        }
        primaryStage.centerOnScreen()
        primaryStage.show()
    }
}

class ChuckNorrixFxView(task: ChuckNorrisFxTask) {
    private var jokeProperty: String by Delegates.observable("") { _, _, newJoke ->
        Platform.runLater { webview.engine.loadContent(newJoke) }
    }

    private val bufferedImage = ImageIO.read(this::class.java.getResourceAsStream("/cn.jpg"))
    private val logo = ImageView().apply {
        image = SwingFXUtils.toFXImage(bufferedImage, null )
        fitHeight = 100.0
        fitWidth = 100.0
        isPreserveRatio = true
        isSmooth = true
    }

    private val webview = WebView()

    private val jokeButton = Button().apply {
        prefWidth = 80.0
        prefHeight = 30.0
        text = "New Joke"
        onAction = EventHandler { _ ->
            Platform.runLater {
                busyIndicator.progress = 50.0
                busyIndicator.isVisible = true
                isDisable = true
            }
            val json = runBlocking { task.getJoke() }
            Platform.runLater {
                busyIndicator.progress = 100.0
                busyIndicator.progress = -1.0
                busyIndicator.isVisible = false
                isDisable = false
            }
            jokeProperty = json.removeSurrounding("\"")
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

class ChuckNorrisFxTask {
    private val client = HttpClient(CIO)

    suspend fun getJoke(): String {
        return runCatching {
            val json = client.get("https://api.chucknorris.io/jokes/random").bodyAsText()
            val jsonElement = Json.parseToJsonElement(json)
            jsonElement.jsonObject["value"].toString()
        }.getOrDefault("Chuck is taking a power nap. Come back later.")
    }
}