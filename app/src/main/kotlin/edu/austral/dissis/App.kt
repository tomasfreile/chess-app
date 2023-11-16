/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package edu.austral.dissis

import checkers.factory.CheckersGameCreator
import chess.factory.ChessGameCreator
import commons.game.GameEngineImpl
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.createGameViewFrom
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    Application.launch(ChessGameApplication::class.java)
}


class ChessGameApplication : Application() {
    private val chess = GameEngineImpl(ChessGameCreator().createGame())
    private val checkers = GameEngineImpl(CheckersGameCreator().createGame())

    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {

        primaryStage.title = GameTitle

        val root = createGameViewFrom(chess, imageResolver)
        //val root = GameView(imageResolver)

        primaryStage.scene = Scene(root)

        primaryStage.show()
    }


}