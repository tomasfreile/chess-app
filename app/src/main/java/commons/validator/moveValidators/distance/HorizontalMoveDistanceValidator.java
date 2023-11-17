package commons.validator.moveValidators.distance;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class HorizontalMoveDistanceValidator implements Validator {

    private final int distance;
    public HorizontalMoveDistanceValidator(int distance) {
        this.distance = distance;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        return from.getRow() == to.getRow() && Math.abs(from.getColumn() - to.getColumn()) == distance;
    }
}
