package commons.validator.moveValidators.distance;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class DiagonalMoveDistanceValidator implements Validator {

    private final int distance;

    public DiagonalMoveDistanceValidator(int distance) {
        this.distance = distance;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        int rowDistance = Math.abs(from.getRow() - to.getRow());
        int columnDistance = Math.abs(from.getColumn() - to.getColumn());
        return rowDistance == columnDistance && rowDistance == distance;
    }
}
