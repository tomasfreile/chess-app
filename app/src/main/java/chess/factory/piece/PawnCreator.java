package chess.factory.piece;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.ArrayList;
import java.util.List;

public class PawnCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> pawnMoves = new ArrayList<>();
        pawnMoves.add(new Movement(1, 1, true, false, false));
        pawnMoves.add(new Movement(1, -1, true, false, false));
        pawnMoves.add(new Movement(1, 0, false, false, false));
        return new Piece(PieceName.PAWN, pawnMoves, color, 0);
    }
}
