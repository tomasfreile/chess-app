package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class StraightMoveDistanceValidator implements Validator {

    private final int distance;

    public StraightMoveDistanceValidator(int distance) {
        this.distance = distance;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        if (from.getColumn() == to.getColumn()){
            return from.getRow() != to.getRow() && Math.abs(from.getRow() - to.getRow()) == distance;
        }
        if (from.getRow() == to.getRow()){
            return from.getColumn() != to.getColumn() && Math.abs(from.getColumn() - to.getColumn()) == distance;
        }
        return false;
    }
}
