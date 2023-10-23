package chess.factory.piece;

import commons.*;

import java.util.ArrayList;
import java.util.List;

public class RookCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> rookMoves = new ArrayList<>();
        rookMoves.add(new Movement(1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, true, false, true));
        rookMoves.add(new Movement(0, -1, true, false, true));
        rookMoves.add(new Movement(-1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, false, false, true));
        rookMoves.add(new Movement(0, -1, false, false, true));
        rookMoves.add(new Movement(1, 0, false, false, true));
        rookMoves.add(new Movement(-1, 0, false, false, true));
        return new Piece(PieceName.ROOK, rookMoves, color, 0);
    }
}
