package commons.validator;

import commons.Board;
import commons.Tile;

import java.util.List;

public class AndValidator implements Validator {
    private final List<Validator> validators;
    public AndValidator(List<Validator> validators) {
        this.validators = validators;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        for (Validator validator : validators) {
            if (!validator.isValid(from, to, board)) {
                return false;
            }
        }
        return true;
    }
}
