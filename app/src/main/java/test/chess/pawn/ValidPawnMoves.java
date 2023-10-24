package test.chess.pawn;

import chess.factory.piece.PawnCreator;
import commons.Board;
import commons.Color;
import commons.Game;
import commons.Tile;
import commons.piece.PieceCreator;
import org.junit.Test;
import test.chess.TestGameCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidPawnMoves {
    PieceCreator pieceCreator = new PawnCreator();
    TestGameCreator testGameCreator = new TestGameCreator();

    @Test
    public void testValidPawnMoves() {
        Game game = testGameCreator.createGame(pieceCreator.createPiece(Color.WHITE));
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(board.getPosition(4, 3));


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
