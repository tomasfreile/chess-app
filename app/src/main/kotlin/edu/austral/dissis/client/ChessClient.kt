package edu.austral.dissis.client

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import edu.austral.ingsis.clientserver.Client
import java.net.InetSocketAddress


class ChessClient(private var gameView: GameView) {
    private var client: Client = build()
    fun start() {
        client.connect()
        client.send(Message("init", Unit))
        gameView.addListener(MovementListener(this))
    }

    fun handleInit(message: Message<InitialState>) {
        gameView.handleInitialState(message.payload)
    }

    fun handleNewGameState(message: Message<NewGameState>) {
        gameView.handleMoveResult(message.payload)
    }

    fun handleInvalidMove(message: Message<InvalidMove>) {
        gameView.handleMoveResult(message.payload)
    }

    fun handleGameOver(message: Message<GameOver>) {
        gameView.handleMoveResult(message.payload)
    }

    fun move(move: Move) {
        client.send(Message("move", move))
    }


    private fun build() : Client {
        return NettyClientBuilder.createDefault()
            .withAddress(InetSocketAddress(8080))
            .addMessageListener(
                "init",
                object : TypeReference<Message<InitialState>>() {},
                InitListener(this)
            )
            .addMessageListener(
                "validMove",
                object : TypeReference<Message<NewGameState>> () {},
                ValidMoveListener(this)
            )
            .addMessageListener(
                "invalidMove",
                object : TypeReference<Message<InvalidMove>> () {},
                InvalidMoveListener(this)
            )
            .addMessageListener(
                "gameOver",
                object : TypeReference<Message<GameOver>> () {},
                GameOverListener(this)
            )
            .build()
    }

}