package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class NoObstaclesHorizontalValidator implements Validator {

    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        int direction = from.getColumn() < to.getColumn() ? 1 : -1;
        for (int i = from.getColumn() + direction; i != to.getColumn(); i += direction) {
            if (board.getPieceAtPosition(from.getRow(), i) != null) {
                return false;
            }
        }
        return true;
    }
}
