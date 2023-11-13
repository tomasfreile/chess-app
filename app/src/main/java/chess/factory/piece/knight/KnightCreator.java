package chess.factory.piece.knight;

import commons.*;
import commons.move.Movement;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.List;

public class KnightCreator implements PieceCreator {

    @Override
    public Piece createPiece(Color color) {
       List<Movement> knightMoves = new KnightMoves().getMoves();
        return new Piece(PieceName.KNIGHT, knightMoves, color, 0);
    }
}
