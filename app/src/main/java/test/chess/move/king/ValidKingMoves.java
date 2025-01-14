package test.chess.move.king;

import chess.factory.piece.PieceFactory;
import commons.Board;
import commons.Color;
import commons.Tile;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceName;
import org.junit.Test;
import test.Helper;
import test.TestBoardCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidKingMoves {
    TestBoardCreator testGameCreator = new TestBoardCreator();

    @Test
    public void testValidKingMoves() {
        Piece piece = PieceFactory.createPiece(PieceName.KING, Color.WHITE);
        Game game = testGameCreator.createGame(piece);
        Board board = game.getBoard();
        System.out.println(game.getBoard().printBoard());

        List<Tile> availableMoves = new ArrayList<>();
        availableMoves.add(new Tile(3, 4));
        availableMoves.add(new Tile(4, 4));
        availableMoves.add(new Tile(4, 3));
        availableMoves.add(new Tile(4, 2));
        availableMoves.add(new Tile(3, 2));
        availableMoves.add(new Tile(2, 2));
        availableMoves.add(new Tile(2, 3));
        availableMoves.add(new Tile(2, 4));



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

