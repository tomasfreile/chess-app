package checkers;

import checkers.factory.piece.CheckersPawnCreator;
import checkers.factory.winConditions.AllPiecesCaptured;
import checkers.factory.winConditions.NoMovesAvailable;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.*;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;

import java.util.ArrayList;
import java.util.List;

public class CheckersGameCreator implements GameCreator {
    public CheckersGameCreator() {
    }

    @Override
    public Game createGame() {
        //checkers piece
        PieceCreator checkerPawnCreator = new CheckersPawnCreator();
        Piece pawnW = checkerPawnCreator.createPiece(Color.WHITE);
        Piece pawnB = checkerPawnCreator.createPiece(Color.BLACK);

        List<Tile> startingPositions = List.of(
                new Tile(0, 1, pawnW),
                new Tile(0, 3, pawnW),
                new Tile(0, 5, pawnW),
                new Tile(0, 7, pawnW),
                new Tile(1, 0, pawnW),
                new Tile(1, 2, pawnW),
                new Tile(1, 4, pawnW),
                new Tile(1, 6, pawnW),
                new Tile(2, 1, pawnW),
                new Tile(2, 3, pawnW),
                new Tile(2, 5, pawnW),
                new Tile(2, 7, pawnW),
                new Tile(5, 0, pawnB),
                new Tile(5, 2, pawnB),
                new Tile(5, 4, pawnB),
                new Tile(5, 6, pawnB),
                new Tile(6, 1, pawnB),
                new Tile(6, 3, pawnB),
                new Tile(6, 5, pawnB),
                new Tile(6, 7, pawnB),
                new Tile(7, 0, pawnB),
                new Tile(7, 2, pawnB),
               new Tile(7, 6, pawnB)

        );

        List<WinCondition> winConditions = List.of(
                new AllPiecesCaptured(),
                new NoMovesAvailable()
        );

        List<StalemateCondition> stalemateConditions = new ArrayList<>();

        Rules rules = new CheckersRules(startingPositions, winConditions, stalemateConditions);

        Board board = new Board(8,8);

        for (Tile tile : rules.getStartingPositions()) {
            board = board.replacePosition(tile);
        }


        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, false, new CheckersPieceMover(), new CheckersMoveVerifier());
    }
}
