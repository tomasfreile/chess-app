package commons.validator;

import commons.Board;
import commons.move.Movement;
import commons.Tile;

public interface Validator {
    boolean validate(Movement movement, Board board, Tile start, Tile end, int incrementRow, int incrementColumn);
}
