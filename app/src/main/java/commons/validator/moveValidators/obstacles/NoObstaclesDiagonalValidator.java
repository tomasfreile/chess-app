package commons.validator.moveValidators.obstacles;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class NoObstaclesDiagonalValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        int rowDirection = from.getRow() < to.getRow() ? 1 : -1;
        int columnDirection = from.getColumn() < to.getColumn() ? 1 : -1;
        int row = from.getRow() + rowDirection;
        int column = from.getColumn() + columnDirection;
        while (row != to.getRow() && column != to.getColumn()) {
            if (board.getPieceAtPosition(row, column) != null) {
                return false;
            }
            row += rowDirection;
            column += columnDirection;
        }
        return true;
    }
}
