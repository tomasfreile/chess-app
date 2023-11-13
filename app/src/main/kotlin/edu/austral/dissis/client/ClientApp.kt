package edu.austral.dissis.client

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    Application.launch(GameUI::class.java)
}

class GameUI : Application() {

    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    companion object {
        const val title = "Chess"
        val client = Client()
    }
    override fun start(primaryStage: Stage) {
        primaryStage.title = title
        val root = GameView(imageResolver)
        primaryStage.scene = Scene(root)
        client.start(root)
        primaryStage.show()
    }

}