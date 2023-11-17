package checkers.factory;

import checkers.CheckersPieceMover;
import checkers.factory.winConditions.AllPiecesCaptured;
import checkers.factory.winConditions.NoMovesAvailable;
import checkers.rules.CheckersRules;
import chess.factory.piece.PieceFactory;
import commons.game.Game;
import commons.game.GameCreator;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.*;
import commons.piece.PieceName;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckersGameCreator implements GameCreator {
    public CheckersGameCreator() {
    }

    @Override
    public Game createGame() {
        //checkers piece
        Piece pawnW = PieceFactory.createPiece(PieceName.PAWN, Color.WHITE);
        Piece pawnB = PieceFactory.createPiece(PieceName.PAWN, Color.BLACK);

        Map<Tile,Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(0,1), pawnB);
        startingPositions.put(new Tile(0,3), pawnB);
        startingPositions.put(new Tile(0,5), pawnB);
        startingPositions.put(new Tile(0,7), pawnB);
        startingPositions.put(new Tile(1,0), pawnB);
        startingPositions.put(new Tile(1,2), pawnB);
        startingPositions.put(new Tile(1,4), pawnB);
        startingPositions.put(new Tile(1,6), pawnB);
        startingPositions.put(new Tile(2,1), pawnB);
        startingPositions.put(new Tile(2,3), pawnB);
        startingPositions.put(new Tile(2,5), pawnB);
        startingPositions.put(new Tile(2,7), pawnB);
        startingPositions.put(new Tile(5,0), pawnW);
        startingPositions.put(new Tile(5,2), pawnW);
        startingPositions.put(new Tile(5,4), pawnW);
        startingPositions.put(new Tile(5,6), pawnW);
        startingPositions.put(new Tile(6,1), pawnW);
        startingPositions.put(new Tile(6,3), pawnW);
        startingPositions.put(new Tile(6,5), pawnW);
        startingPositions.put(new Tile(6,7), pawnW);
        startingPositions.put(new Tile(7,0), pawnW);
        startingPositions.put(new Tile(7,2), pawnW);
        startingPositions.put(new Tile(7,4), pawnW);
        startingPositions.put(new Tile(7,6), pawnW);

        List<WinCondition> winConditions = List.of(
                new AllPiecesCaptured(),
                new NoMovesAvailable()
        );

        List<StalemateCondition> stalemateConditions = new ArrayList<>();

        Rules rules = new CheckersRules(winConditions, stalemateConditions);

        Board board = new Board(startingPositions,8,8);




        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, new CheckersPieceMover());
    }
}
