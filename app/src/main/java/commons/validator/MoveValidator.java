package commons.validator;

import commons.Board;
import commons.Tile;

public interface MoveValidator {
    boolean isValid(Tile from, Tile to, Board board);
}
