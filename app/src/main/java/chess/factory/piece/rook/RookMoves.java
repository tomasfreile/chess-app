package chess.factory.piece.rook;

import commons.Movement;

import java.util.ArrayList;
import java.util.List;

public class RookMoves {
    public List<Movement> getMoves() {
        List<Movement> rookMoves = new ArrayList<>();
        rookMoves.add(new Movement(1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, true, false, true));
        rookMoves.add(new Movement(0, -1, true, false, true));
        rookMoves.add(new Movement(-1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, false, false, true));
        rookMoves.add(new Movement(0, -1, false, false, true));
        rookMoves.add(new Movement(1, 0, false, false, true));
        rookMoves.add(new Movement(-1, 0, false, false, true));
        return rookMoves;
    }
}
