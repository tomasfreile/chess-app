package chess.factory.piece.pawn;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.List;

public class PawnCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> pawnMoves = new PawnMoves().getMoves();
        return new Piece(PieceName.PAWN, pawnMoves, color, 0);
    }
}
