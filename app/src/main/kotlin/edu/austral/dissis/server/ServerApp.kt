package edu.austral.dissis.server

import chess.factory.ChessGameCreator

fun main() {
    val chess = ChessGameCreator().createGame()
    Server(chess)
}