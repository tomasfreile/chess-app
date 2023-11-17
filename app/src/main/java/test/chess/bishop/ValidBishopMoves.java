package test.chess.bishop;

import commons.Board;
import commons.Color;
import commons.game.Game;
import commons.Tile;
import commons.piece.PieceCreator;
import org.junit.Test;
import test.chess.TestChessGameCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidBishopMoves {
    TestChessGameCreator testGameCreator = new TestChessGameCreator();

    @Test
    public void testValidBishopMoves() {
//        Game game = testGameCreator.createGame(pieceCreator.createPiece(Color.WHITE));
//        Board board = game.getBoard();
//        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
//        availableMoves.add(board.getPosition(0, 0));
//        availableMoves.add(board.getPosition(1, 1));
//        availableMoves.add(board.getPosition(2, 2));
//        availableMoves.add(board.getPosition(4, 4));
//        availableMoves.add(board.getPosition(5, 5));
//        availableMoves.add(board.getPosition(6, 6));
//        availableMoves.add(board.getPosition(7, 7));
//        availableMoves.add(board.getPosition(0, 6));
//        availableMoves.add(board.getPosition(1, 5));
//        availableMoves.add(board.getPosition(2, 4));
//        availableMoves.add(board.getPosition(4, 2));
//        availableMoves.add(board.getPosition(5, 1));
//        availableMoves.add(board.getPosition(6, 0));
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
