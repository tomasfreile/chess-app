package chess.factory.piece.knight;

import commons.move.Movement;


import java.util.ArrayList;
import java.util.List;

public class KnightMoves {
    public List<Movement> getMoves() {
        List<Movement> knightMoves = new ArrayList<>();
        knightMoves.add(new Movement(1, 2, true, true, false));
        knightMoves.add(new Movement(2, 1, true, true, false));
        knightMoves.add(new Movement(-1, 2, true, true, false));
        knightMoves.add(new Movement(-2, 1, true, true, false));
        knightMoves.add(new Movement(1, -2, true, true, false));
        knightMoves.add(new Movement(2, -1, true, true, false));
        knightMoves.add(new Movement(-1, -2, true, true, false));
        knightMoves.add(new Movement(-2, -1, true, true, false));
        knightMoves.add(new Movement(1, 2, false, true, false));
        knightMoves.add(new Movement(2, 1, false, true, false));
        knightMoves.add(new Movement(-1, 2, false, true, false));
        knightMoves.add(new Movement(-2, 1, false, true, false));
        knightMoves.add(new Movement(1, -2, false, true, false));
        knightMoves.add(new Movement(2, -1, false, true, false));
        knightMoves.add(new Movement(-1, -2, false, true, false));
        knightMoves.add(new Movement(-2, -1, false, true, false));
        return knightMoves;
    }
}
