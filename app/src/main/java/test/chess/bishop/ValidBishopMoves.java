package test.chess.bishop;

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

public class ValidBishopMoves {
    TestBoardCreator testGameCreator = new TestBoardCreator();

    @Test
    public void testValidBishopMoves() {
        Piece piece = PieceFactory.createPiece(PieceName.BISHOP, Color.WHITE);
        Game game = testGameCreator.createGame(piece);
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(new Tile(0, 0));
        availableMoves.add(new Tile(1, 1));
        availableMoves.add(new Tile(2, 2));
        availableMoves.add(new Tile(4, 4));
        availableMoves.add(new Tile(5, 5));
        availableMoves.add(new Tile(6, 6));
        availableMoves.add(new Tile(7, 7));
        availableMoves.add(new Tile(0, 6));
        availableMoves.add(new Tile(1, 5));
        availableMoves.add(new Tile(2, 4));
        availableMoves.add(new Tile(4, 2));
        availableMoves.add(new Tile(5, 1));
        availableMoves.add(new Tile(6, 0));

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
