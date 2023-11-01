package chess.factory.piece.bishop;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.List;

public class BishopCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> moves = new BishopMoves().getMoves();
        return new Piece(PieceName.BISHOP, moves, color, 0);
    }
}