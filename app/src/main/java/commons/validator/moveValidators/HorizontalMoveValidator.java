package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class HorizontalMoveValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        return from.getColumn() != to.getColumn() && from.getRow() == to.getRow();
    }
}
