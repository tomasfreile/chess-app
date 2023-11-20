package test;

import chess.ChessPieceMover;
import commons.Board;
import commons.Color;
import commons.Player;
import commons.Tile;
import commons.game.Game;
import commons.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class TestBoardCreator {
    public TestBoardCreator() {
    }
    public Game createGame(Piece piece) {
        Map<Tile, Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(3, 3), piece);

        Board board = new Board(startingPositions, 8, 8);

        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, null, player1, new ChessPieceMover());
    }
}
