package test.checkers.capture;

import checkers.CheckersPieceMover;
import checkers.factory.piece.CheckersPieceFactory;
import checkers.rules.CheckersRules;
import commons.Board;
import commons.Color;
import commons.Player;
import commons.Tile;
import commons.game.Game;
import commons.piece.Piece;
import commons.result.GameMoveResult;
import commons.result.UnsuccessfulMove;
import commons.rules.Rules;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultipleCaptureTest {

    Rules rules = new CheckersRules(new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    Player player1 = new Player(Color.WHITE, "Player 1");
    Player player2 = new Player(Color.BLACK, "Player 2");
    Piece pawnW = CheckersPieceFactory.createPawn(Color.WHITE);
    Piece pawnB = CheckersPieceFactory.createPawn(Color.BLACK);

    @Test
    public void canDoubleCaptureStraightForward() {
        Map<Tile, Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(0, 1), pawnW);
        startingPositions.put(new Tile(1, 2), pawnB);
        startingPositions.put(new Tile(3, 4), pawnB);

        Board board = new Board(startingPositions, 8, 8);

        Game game = new Game(board, player1, player2, rules, player1, new CheckersPieceMover());
        System.out.println(game.getBoard().printBoard());

        Game move1 = game.move(new Tile(0, 1), new Tile(2, 3)).getGame();
        Game move2 = move1.move(new Tile(2, 3), new Tile(4, 5)).getGame();

        assertFalse(move2.getBoard().isPositionOccupied(new Tile(1, 2)));
        assertFalse(move2.getBoard().isPositionOccupied(new Tile(3, 4)));
        assertEquals(game.getCurrentPlayer(), move1.getCurrentPlayer());
        assertNotEquals(game.getCurrentPlayer(), move2.getCurrentPlayer());
    }

    @Test
    public void canDoubleCaptureChangingDirectionForward() {
        Map<Tile, Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(0, 1), pawnW);
        startingPositions.put(new Tile(1, 2), pawnB);
        startingPositions.put(new Tile(3, 2), pawnB);

        Board board = new Board(startingPositions, 8, 8);

        Game game = new Game(board, player1, player2, rules, player1, new CheckersPieceMover());
        System.out.println(game.getBoard().printBoard());

        Game move1 = game.move(new Tile(0, 1), new Tile(2, 3)).getGame();
        Game move2 = move1.move(new Tile(2, 3), new Tile(4, 1)).getGame();

        assertFalse(move2.getBoard().isPositionOccupied(new Tile(1, 2)));
        assertFalse(move2.getBoard().isPositionOccupied(new Tile(3, 4)));
        assertEquals(game.getCurrentPlayer(), move1.getCurrentPlayer());
        assertNotEquals(game.getCurrentPlayer(), move2.getCurrentPlayer());
    }

    @Test
    public void cannotDoubleCaptureOverOwnPiece() {
        Map<Tile, Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(0, 1), pawnW);
        startingPositions.put(new Tile(1, 2), pawnB);
        startingPositions.put(new Tile(3, 4), pawnW);

        Board board = new Board(startingPositions, 8, 8);

        Game game = new Game(board, player1, player2, rules, player1, new CheckersPieceMover());
        System.out.println(game.getBoard().printBoard());

        GameMoveResult move1 = game.move(new Tile(0, 1), new Tile(2, 3));
        GameMoveResult move2 = move1.getGame().move(new Tile(2, 3), new Tile(4, 5));

        assertFalse(move1.getGame().getBoard().isPositionOccupied(new Tile(1, 2)));
        assertNotEquals(move1.getGame().getCurrentPlayer(), game.getCurrentPlayer());
        assertTrue(move2 instanceof UnsuccessfulMove);
    }
}
