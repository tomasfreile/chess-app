package test.chess.pawn;

import chess.factory.piece.PieceFactory;
import commons.Board;
import commons.Color;
import commons.game.Game;
import commons.Tile;
import commons.piece.Piece;
import commons.piece.PieceName;
import org.junit.Test;
import test.Helper;
import test.TestBoardCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidPawnMoves {
    TestBoardCreator testGameCreator = new TestBoardCreator();

    @Test
    public void testValidPawnMoves() {
        Piece piece = PieceFactory.createPiece(PieceName.PAWN, Color.WHITE);
        Game game = testGameCreator.createGame(piece);
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(new Tile(4, 3));
        availableMoves.add(new Tile(5, 3));



        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Tile p = new Tile(i, j);
                if (Helper.containsTile(availableMoves, p)) {
                    assertTrue(piece.getMoves().isValid(new Tile(3,3), p, board));
                } else {
                    assertFalse(piece.getMoves().isValid(new Tile(3,3), p, board));
                }
            }
        }
    }
}
