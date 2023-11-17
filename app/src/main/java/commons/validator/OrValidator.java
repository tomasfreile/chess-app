package commons.validator;

import commons.Board;
import commons.Tile;

import java.util.List;

public class OrValidator implements MoveValidator{
    private final List<MoveValidator> validators;
    public OrValidator(List<MoveValidator> validators) {
        this.validators = validators;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        for  (MoveValidator validator : validators) {
            if (validator.isValid(from, to, board)) {
                return true;
            }
        }
        return false;
    }
}
