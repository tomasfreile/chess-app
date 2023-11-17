package edu.austral.dissis.server

import com.fasterxml.jackson.core.type.TypeReference
import commons.Color
import commons.game.Game
import commons.adapter.PieceTranslator
import commons.game.GameEngineImpl
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

class ChessServer(private var game: Game, private val builder: ServerBuilder = NettyServerBuilder.createDefault()) {
    private var server: Server
   // private var pieces: MutableList<ChessPiece> = PieceTranslator().translatePieceList(game.board.positions)
    private val gameEngine = GameEngineImpl(game)

    init {
        server = buildServer()
        server.start()
    }

    private fun buildServer() : Server {
        return builder
            .withPort(8080)
            .addMessageListener(
                "move",
                object : TypeReference<Message<Move>>() {},
                MoveListener(this))
            .addMessageListener(
                "init",
                object : TypeReference<Message<Unit>> () {},
                InitListener(this)
            )
            .build()
    }



    fun handleMove(move: Move) {
        when (val result = gameEngine.applyMove(move)){
            is GameOver -> server.broadcast(Message("gameOver", result))
            is InvalidMove -> server.broadcast(Message("invalidMove", result))
            is NewGameState -> server.broadcast(Message("validMove", result))
        }
    }


    fun handleInit() {
        server.broadcast(Message("init", InitialState(
            BoardSize(game.board.width, game.board.height),
            PieceTranslator().translatePieceList(game.board.positions),
            if (game.currentPlayer.color == Color.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
        )
        ))
    }


}