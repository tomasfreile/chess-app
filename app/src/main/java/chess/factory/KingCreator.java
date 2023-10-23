package chess.factory;

import commons.PieceName;
import commons.Color;
import commons.Movement;
import commons.Piece;

import java.util.ArrayList;
import java.util.List;

public class KingCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
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
        return new Piece(PieceName.KING, kingMoves, color, 0);
    }
}
