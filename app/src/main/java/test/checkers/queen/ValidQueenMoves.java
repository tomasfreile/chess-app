package test.checkers.queen;

import checkers.factory.piece.CheckersQueenCreator;
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

public class ValidQueenMoves {
    PieceCreator pieceCreator = new CheckersQueenCreator();

    TestCheckersGameCreator testGameCreator = new TestCheckersGameCreator();

    @Test
    public void testValidQueenMoves() {
        Game game = testGameCreator.createGame(pieceCreator.createPiece(Color.WHITE));
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(new Tile(2, 2));
        availableMoves.add(new Tile(2, 4));
        availableMoves.add(new Tile(4, 2));
        availableMoves.add(new Tile(4, 4));
        availableMoves.add(new Tile(5, 5));
        availableMoves.add(new Tile(5, 1));
        availableMoves.add(new Tile(1, 5));
        availableMoves.add(new Tile(1, 1));
        availableMoves.add(new Tile(6, 6));
        availableMoves.add(new Tile(6, 0));
        availableMoves.add(new Tile(0, 6));
        availableMoves.add(new Tile(0, 0));
        availableMoves.add(new Tile(7,7));
//        availableMoves.add(board.getPosition(0, 6));
//        availableMoves.add(board.getPosition(0, 0));
//        availableMoves.add(board.getPosition(7,7));


        for (Tile tile : availableMoves) {
            assertTrue(game.getBoard().getPositions().containsKey(tile));
        }

    }


}
