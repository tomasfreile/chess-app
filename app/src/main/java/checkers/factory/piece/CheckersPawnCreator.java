package checkers.factory.piece;

import commons.PieceCreator;
import commons.Color;
import commons.Movement;
import commons.Piece;
import commons.PieceName;

import java.util.ArrayList;
import java.util.List;

public class CheckersPawnCreator implements PieceCreator {

    public CheckersPawnCreator() {
    }
    @Override
    public Piece createPiece(Color color) {
        List<Movement> checkerPawnMoves = new ArrayList<>();
        checkerPawnMoves.add(new Movement(1, 1, false, false, false));
        checkerPawnMoves.add(new Movement(1, -1, false, false, false));
        checkerPawnMoves.add(new Movement(2, 2, true, true, false));
        checkerPawnMoves.add(new Movement(2, -2, true, true, false));
        return new Piece(PieceName.PAWN, checkerPawnMoves, color, 0);
    }
}