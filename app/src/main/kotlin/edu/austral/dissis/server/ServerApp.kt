package edu.austral.dissis.server

import checkers.factory.CheckersGameCreator
import chess.factory.ChessGameCreator

fun main() {
    val chess = ChessGameCreator().createGame()
    val checkers = CheckersGameCreator().createGame()
    //val capablanca = CapablancaChessGameCreator().createGame()
    ChessServer(chess)
}