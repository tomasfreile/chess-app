package commons.validator;

import commons.Board;
import commons.move.Movement;
import commons.Tile;

import java.util.List;

public interface MoveVerifier {
    boolean verifyPieceMovements(Board board, List<Movement> moves, Tile start, Tile end, int incrementRow, int incrementColumn);
}
