package chess.factory.piece;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.ArrayList;
import java.util.List;

public class KnightCreator implements PieceCreator {

    @Override
    public Piece createPiece(Color color) {
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
        return new Piece(PieceName.KNIGHT, knightMoves, color, 0);
    }
}
