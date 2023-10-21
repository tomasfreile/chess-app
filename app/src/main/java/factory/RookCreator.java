package factory;

import chess.PieceName;
import commons.Color;
import commons.Movement;
import commons.Piece;

import java.util.ArrayList;
import java.util.List;

public class RookCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> rookMoves = new ArrayList<Movement>();
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
