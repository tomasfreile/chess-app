package chess.factory.piece.queen;

import commons.*;
import commons.move.Movement;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.List;

public class QueenCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
       List<Movement> queenMoves = new QueenMoves().getMoves();
        return new Piece(PieceName.QUEEN, queenMoves, color, 0);
    }
}
