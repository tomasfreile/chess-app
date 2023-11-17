package test.checkers.pawn;

import checkers.factory.piece.CheckersPawnCreator;
import commons.Board;
import commons.Color;
import commons.game.Game;
import commons.Tile;
import commons.piece.PieceCreator;
import org.junit.Test;
import test.checkers.TestCheckersGameCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidPawnMoves {
    PieceCreator pieceCreator = new CheckersPawnCreator();
    TestCheckersGameCreator testGameCreator = new TestCheckersGameCreator();

    @Test
    public void testValidPawnMoves() {
        Game game = testGameCreator.createGame(pieceCreator.createPiece(Color.WHITE));
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(new Tile(2, 2));
        availableMoves.add(new Tile(2, 4));


        for (Tile tile : availableMoves) {
            assertTrue(game.getBoard().getPositions().containsKey(tile));
        }


    }
}
