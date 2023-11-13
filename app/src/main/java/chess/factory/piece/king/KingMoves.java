package chess.factory.piece.king;

import commons.move.Movement;

import java.util.ArrayList;
import java.util.List;

public class KingMoves {
    public List<Movement> getMoves() {
        List<Movement> kingMoves = new ArrayList<>();
        kingMoves.add(new Movement(1, 1, true, false, false));
        kingMoves.add(new Movement(-1, -1, true, false, false));
        kingMoves.add(new Movement(1, -1, true, false, false));
        kingMoves.add(new Movement(-1, 1, true, false, false));
        kingMoves.add(new Movement(0, 1, true, false, false));
        kingMoves.add(new Movement(0, -1, true, false, false));
        kingMoves.add(new Movement(1, 0, true, false, false));
        kingMoves.add(new Movement(-1, 0, true, false, false));
        kingMoves.add(new Movement(1, 1, false, false, false));
        kingMoves.add(new Movement(-1, -1, false, false, false));
        kingMoves.add(new Movement(1, -1, false, false, false));
        kingMoves.add(new Movement(-1, 1, false, false, false));
        kingMoves.add(new Movement(0, 1, false, false, false));
        kingMoves.add(new Movement(0, -1, false, false, false));
        kingMoves.add(new Movement(1, 0, false, false, false));
        kingMoves.add(new Movement(-1, 0, false, false, false));
        return kingMoves;
    }
}
