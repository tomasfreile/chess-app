package test.checkers;

import checkers.CheckersPieceMover;
import checkers.rules.CheckersRules;
import commons.*;
import commons.game.Game;
import commons.piece.Piece;
import commons.rules.Rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestCheckersGameCreator {
    public TestCheckersGameCreator() {
    }
    public Game createGame(Piece piece) {
        Map<Tile,Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(3,3), piece);

        Rules rules = new CheckersRules(new ArrayList<>(), new ArrayList<>());
        Board board = new Board(startingPositions,8,8);


        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, new CheckersPieceMover());
    }
}
