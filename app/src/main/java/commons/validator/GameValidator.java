package commons.validator;

import commons.Board;
import commons.Color;
import commons.Tile;

public interface GameValidator {
    boolean isValid(Tile from, Tile to, Board board, Color color);
}
