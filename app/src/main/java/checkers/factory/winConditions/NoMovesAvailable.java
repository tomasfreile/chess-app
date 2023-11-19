package checkers.factory.winConditions;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.piece.Piece;
import commons.rules.WinCondition;

import java.util.Map;

public class NoMovesAvailable implements WinCondition {


    @Override
    public boolean checkWin(Board board, Color color) {
        return cannotMove(board, color);
    }

    private boolean cannotMove(Board board, Color color) {
        for (Tile tile : board.getPositions().keySet()) {
            if (board.getPieceAtPosition(tile.getRow(), tile.getColumn()).getColor() == color) {
                for (int row = 0; row < board.getHeight(); row++) {
                    for (int column = 0; column < board.getWidth(); column++) {
                        Tile to = new Tile(row, column);
                        if (board.getPieceAtPosition(row, column) == null) {
                            if (board.getPieceAtPosition(tile.getRow(), tile.getColumn()).getMoves().isValid(tile, to, board)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
