package factory;

import chess.PieceName;
import commons.Color;
import commons.Movement;
import commons.Piece;

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
