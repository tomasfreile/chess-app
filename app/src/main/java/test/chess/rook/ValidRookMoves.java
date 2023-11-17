package test.chess.rook;

import commons.*;
import commons.game.Game;
import commons.piece.PieceCreator;
import org.junit.Test;
import test.chess.TestChessGameCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidRookMoves {
    TestChessGameCreator testGameCreator = new TestChessGameCreator();

    @Test
    public void testValidRookMoves() {
//        Game game = testGameCreator.createGame(pieceCreator.createPiece(Color.WHITE));
//        Board board = game.getBoard();
//        System.out.println(game.getBoard().printBoard());
//
//        List<Tile> availableMoves = new ArrayList<>();
//        availableMoves.add(board.getPosition(3, 4));
//        availableMoves.add(board.getPosition(3, 5));
//        availableMoves.add(board.getPosition(3, 6));
//        availableMoves.add(board.getPosition(3, 7));
//        availableMoves.add(board.getPosition(3, 2));
//        availableMoves.add(board.getPosition(3, 1));
//        availableMoves.add(board.getPosition(3, 0));
//        availableMoves.add(board.getPosition(4, 3));
//        availableMoves.add(board.getPosition(5, 3));
//        availableMoves.add(board.getPosition(6, 3));
//        availableMoves.add(board.getPosition(7, 3));
//        availableMoves.add(board.getPosition(2, 3));
//        availableMoves.add(board.getPosition(1, 3));
//        availableMoves.add(board.getPosition(0, 3));
//
//
//        for (Tile p : board.getPositions()){
//            if (availableMoves.contains(p)){
//                assertTrue(game.getMoveHandler().validateMovement(board.getPosition(3,3), p, board, game.getMoveVerifier()));
//            }
//            else {
//                assertFalse(game.getMoveHandler().validateMovement(board.getPosition(3,3), p, board, game.getMoveVerifier()));
//            }
//        }

    }


}
