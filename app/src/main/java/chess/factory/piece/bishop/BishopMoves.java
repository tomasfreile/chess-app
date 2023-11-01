package chess.factory.piece.bishop;

import commons.Movement;

import java.util.ArrayList;
import java.util.List;

public class BishopMoves {
    public List<Movement> getMoves() {
        List<Movement> moves = new ArrayList<>();
        moves.add(new Movement(1, 1, true, false, true));
        moves.add(new Movement(-1, -1, true, false, true));
        moves.add(new Movement(1, -1, true, false, true));
        moves.add(new Movement(-1, 1, true, false, true));
        moves.add(new Movement(1, 1, false, false, true));
        moves.add(new Movement(-1, -1, false, false, true));
        moves.add(new Movement(1, -1, false, false, true));
        moves.add(new Movement(-1, 1, false, false, true));
        return moves;
    }


}
