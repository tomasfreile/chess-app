package test.checkers;

import checkers.CheckersMoveVerifier;
import checkers.CheckersPieceMover;
import checkers.CheckersRules;
import commons.*;
import commons.piece.Piece;
import commons.rules.Rules;

import java.util.ArrayList;
import java.util.List;

public class TestCheckersGameCreator {
    public TestCheckersGameCreator() {
    }
    public Game createGame(Piece piece) {
        List<Tile> startingPositions = new ArrayList<>();
        startingPositions.add(new Tile(3, 3, piece));
        Rules rules = new CheckersRules(startingPositions, new ArrayList<>(), new ArrayList<>());
        Board board = new Board(8,8);
        for (Tile tile : rules.getStartingPositions()) {
            board = board.replacePosition(tile);
        }

        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, false, new CheckersPieceMover(), new CheckersMoveVerifier());
    }
}
