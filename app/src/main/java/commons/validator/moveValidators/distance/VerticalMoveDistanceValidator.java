package commons.validator.moveValidators.distance;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class VerticalMoveDistanceValidator implements Validator {

    private final int distance;
    public VerticalMoveDistanceValidator(int distance) {
        this.distance = distance;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        return from.getColumn() == to.getColumn() && Math.abs(from.getRow() - to.getRow()) == distance;
    }
}
