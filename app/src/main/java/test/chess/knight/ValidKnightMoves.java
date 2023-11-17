package test.chess.knight;

import chess.factory.piece.PieceFactory;
import commons.Board;
import commons.Color;
import commons.game.Game;
import commons.Tile;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;
import org.junit.Test;
import test.chess.Helper;
import test.chess.TestChessGameCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidKnightMoves {
    TestChessGameCreator testGameCreator = new TestChessGameCreator();

    @Test
    public void testValidKnightMoves() {
        Piece piece = PieceFactory.createPiece(PieceName.KNIGHT, Color.WHITE);
        Game game = testGameCreator.createGame(piece);
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(new Tile(1, 2));
        availableMoves.add(new Tile(2, 1));
        availableMoves.add(new Tile(4, 1));
        availableMoves.add(new Tile(5, 2));
        availableMoves.add(new Tile(1, 4));
        availableMoves.add(new Tile(2, 5));
        availableMoves.add(new Tile(4, 5));
        availableMoves.add(new Tile(5, 4));


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
