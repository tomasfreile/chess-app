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
//        Map<Tile, Piece> pieces = board.getPositions();
//        for (Piece piece : pieces.values()) {
//            if (piece.getColor() == color) {
//                for (int row = 0; row < board.getHeight(); row++) {
//                    for (int column = 0; column < board.getWidth(); column++) {
//                        Tile start = new Tile(row, column);
//                        Tile end = new Tile(row + 1, column + 1);
//                        if (moveHandler.validateMovement(start, end, board, moveVerifier)) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
        return true;
    }
}
