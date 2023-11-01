package test.checkers.queen;

import checkers.factory.piece.CheckersQueenCreator;
import commons.Board;
import commons.Color;
import commons.Game;
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
        availableMoves.add(board.getPosition(4, 4));
        availableMoves.add(board.getPosition(4, 2));
        availableMoves.add(board.getPosition(2, 4));
        availableMoves.add(board.getPosition(2, 2));
        availableMoves.add(board.getPosition(5, 5));
        availableMoves.add(board.getPosition(5, 1));
        availableMoves.add(board.getPosition(1, 5));
        availableMoves.add(board.getPosition(1, 1));
        availableMoves.add(board.getPosition(6, 6));
        availableMoves.add(board.getPosition(6, 0));
        availableMoves.add(board.getPosition(0, 6));
        availableMoves.add(board.getPosition(0, 0));
        availableMoves.add(board.getPosition(7,7));



        for (Tile p : board.getPositions()){
            if (availableMoves.contains(p)){
                assertTrue(game.getMoveHandler().validateMovement(board.getPosition(3,3), p, board, game.getMoveVerifier()));
            }
            else {
                assertFalse(game.getMoveHandler().validateMovement(board.getPosition(3,3), p, board, game.getMoveVerifier()));
            }
        }

    }


}