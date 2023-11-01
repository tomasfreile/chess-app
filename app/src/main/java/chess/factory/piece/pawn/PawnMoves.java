package chess.factory.piece.pawn;

import commons.Movement;

import java.util.ArrayList;
import java.util.List;

public class PawnMoves {
    public List<Movement> getMoves() {
        List<Movement> pawnMoves = new ArrayList<>();
        pawnMoves.add(new Movement(1, 1, true, false, false));
        pawnMoves.add(new Movement(1, -1, true, false, false));
        pawnMoves.add(new Movement(1, 0, false, false, false));
        return pawnMoves;
    }
}
