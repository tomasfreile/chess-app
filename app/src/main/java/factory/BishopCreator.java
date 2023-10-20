package factory;

import chess.PieceName;
import commons.Color;
import commons.Movement;
import commons.Piece;

import java.util.ArrayList;
import java.util.List;

public class BishopCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> moves = new ArrayList<Movement>();
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