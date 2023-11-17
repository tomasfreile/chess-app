package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class KnightMoveValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        if (from.getRow() != to.getRow() && from.getColumn() != to.getColumn()) {
           return Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getColumn() - to.getColumn()) == 3;
    }
        return false;
    }
}
