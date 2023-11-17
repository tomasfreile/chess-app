package commons.validator;

import commons.Board;
import commons.Tile;

public interface Validator {
    boolean isValid(Tile from, Tile to, Board board);
}
