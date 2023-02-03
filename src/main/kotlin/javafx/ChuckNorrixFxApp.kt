package javafx

import javafx.application.Application
import javafx.stage.Stage

class ChuckNorrixFxApp : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Chuck Norris Jokes"
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(ChuckNorrixFxApp::class.java, *args)
}