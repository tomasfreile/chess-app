package edu.austral.dissis.server

import com.fasterxml.jackson.core.type.TypeReference
import commons.Color
import commons.Player
import commons.game.Game
import commons.adapter.PieceTranslator
import commons.result.SuccessfulMove
import commons.result.UnsuccessfulMove
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

class Server(private var game: Game, private val builder: ServerBuilder = NettyServerBuilder.createDefault()) {
    private var server: Server
    private var pieces: MutableList<ChessPiece> = PieceTranslator().translatePieceList(game.board.positions)
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

    private fun handleColor(winner: Player): PlayerColor {
        return when (winner.color) {
            Color.WHITE -> PlayerColor.WHITE
            Color.BLACK -> PlayerColor.BLACK
        }

    }

    fun handleMove(move: Move) {
        val from = game.board.getPosition(move.from.row - 1, move.from.column - 1)
        val to = game.board.getPosition(move.to.row - 1, move.to.column - 1)
        when (val result = game.moveAndSwitchPlayer(from, to)) {
            is SuccessfulMove -> server.broadcast(Message("nextMoveResult", handleNextMove(result, move)))
            is UnsuccessfulMove -> server.broadcast(Message("sameMoveResult", InvalidMove(result.message)))
        }
    }

    private fun handleNextMove(result: SuccessfulMove, move: Move): MoveResult {
        val resultGame = result.game
        game = resultGame
        val movedPiece = findPiece(Position(move.from.row, move.from.column))!!
        pieces.remove(movedPiece)

        val capturedPiece = findCapturedPiece(move)
        if (capturedPiece != null) {
            pieces.remove(capturedPiece)
        }

        val pieceId = if (isPromotion(move, movedPiece)) "queen" else movedPiece.pieceId

        return updateGameState(movedPiece, move, pieceId, resultGame)
    }

    fun handleInit() {
        server.broadcast(Message("initResult", InitialState(
            BoardSize(game.board.height, game.board.width),
            PieceTranslator().translatePieceList(game.board.positions),
            handleColor(game.currentPlayer)
        )
        ))
    }

    fun stop() { server.stop() }

    private fun updateGameState(movedPiece: ChessPiece, move: Move, movedPieceName: String, result: Game): MoveResult {
        val updatedPiece = ChessPiece(movedPiece.id, movedPiece.color, move.to, movedPieceName)
        pieces.add(updatedPiece)
        return if (result.isGameOver) {
            println("Game over")
            GameOver(if (result.currentPlayer == result.player2) PlayerColor.WHITE else PlayerColor.BLACK)
        } else {
            println("Next move")
            NewGameState(
                pieces,
                if (result.currentPlayer.color == Color.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
            )
        }
    }

    private fun findPiece(position: Position): ChessPiece? {
        for (piece in pieces) {
            if (piece.position.row == position.row && piece.position.column == position.column) {
                return piece
            }
        }
        return null
    }

    private fun isPromotion(move: Move, movedPiece: ChessPiece): Boolean {
        return movedPiece.pieceId == "pawn" && (move.to.row == 8 && movedPiece.color == PlayerColor.WHITE || move.to.row == 1 && movedPiece.color == PlayerColor.BLACK)
    }

    private fun findCapturedPiece(move: Move): ChessPiece? {
        val capturedTile = game.pieceMover.getCaptureTile(
            game.board.getPosition(move.from.row - 1, move.from.column - 1),
            game.board.getPosition(move.to.row - 1, move.to.column - 1)
        )
        return findPiece(Position(capturedTile.row + 1, capturedTile.column + 1))
    }


}