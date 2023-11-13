package chess.factory.piece.rook;

import commons.*;
import commons.move.Movement;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.List;

public class RookCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> rookMoves = new RookMoves().getMoves();
        return new Piece(PieceName.ROOK, rookMoves, color, 0);
    }
}
