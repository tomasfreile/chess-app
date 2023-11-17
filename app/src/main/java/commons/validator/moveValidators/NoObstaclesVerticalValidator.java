package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class NoObstaclesVerticalValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        int direction = from.getRow() < to.getRow() ? 1 : -1;
        for (int i = from.getRow() + direction; i != to.getRow(); i += direction) {
            if (board.getPieceAtPosition(i, from.getColumn()) != null) {
                return false;
            }
        }
        return true;
    }
}
