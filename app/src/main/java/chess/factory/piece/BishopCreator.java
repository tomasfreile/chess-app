package chess.factory.piece;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.ArrayList;
import java.util.List;

public class BishopCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> moves = new ArrayList<>();
        moves.add(new Movement(1, 1, true, false, true));
        moves.add(new Movement(-1, -1, true, false, true));
        moves.add(new Movement(1, -1, true, false, true));
        moves.add(new Movement(-1, 1, true, false, true));
        moves.add(new Movement(1, 1, false, false, true));
        moves.add(new Movement(-1, -1, false, false, true));
        moves.add(new Movement(1, -1, false, false, true));
        moves.add(new Movement(-1, 1, false, false, true));

        return new Piece(PieceName.BISHOP, moves, color, 0);
    }
}