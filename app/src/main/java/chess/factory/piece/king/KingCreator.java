package chess.factory.piece.king;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.List;

public class KingCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List <Movement> kingMoves = new KingMoves().getMoves();
        return new Piece(PieceName.KING, kingMoves, color, 0);
    }
}
