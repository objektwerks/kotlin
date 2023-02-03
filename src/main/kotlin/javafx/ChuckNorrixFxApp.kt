package javafx

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

class ChuckNorrixFxApp : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Chuck Norris Jokes"

        val label = Label("Test joke!")
        val scene = Scene(label, 400.0, 300.0)

        primaryStage.scene = scene
        primaryStage.centerOnScreen()
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(ChuckNorrixFxApp::class.java, *args)
}