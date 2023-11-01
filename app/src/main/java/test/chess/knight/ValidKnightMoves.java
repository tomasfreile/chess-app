package test.chess.knight;

import chess.factory.piece.knight.KnightCreator;
import commons.Board;
import commons.Color;
import commons.Game;
import commons.Tile;
import commons.piece.PieceCreator;
import org.junit.Test;
import test.chess.TestChessGameCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidKnightMoves {
    PieceCreator pieceCreator = new KnightCreator();
    TestChessGameCreator testGameCreator = new TestChessGameCreator();

    @Test
    public void testValidKnightMoves() {
        Game game = testGameCreator.createGame(pieceCreator.createPiece(Color.WHITE));
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(board.getPosition(5, 4));
        availableMoves.add(board.getPosition(5, 2));
        availableMoves.add(board.getPosition(4, 5));
        availableMoves.add(board.getPosition(4, 1));
        availableMoves.add(board.getPosition(2, 5));
        availableMoves.add(board.getPosition(2, 1));
        availableMoves.add(board.getPosition(1, 4));
        availableMoves.add(board.getPosition(1, 2));


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
