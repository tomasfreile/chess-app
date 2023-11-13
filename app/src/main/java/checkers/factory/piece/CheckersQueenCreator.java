package checkers.factory.piece;

import commons.*;
import commons.move.Movement;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.ArrayList;
import java.util.List;

public class CheckersQueenCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> checkerQueenMoves = new ArrayList<>();
        checkerQueenMoves.add(new Movement(1, 1, false, false, true));
        checkerQueenMoves.add(new Movement(1, -1, false, false, true));
        checkerQueenMoves.add(new Movement(-1, 1, false, false, true));
        checkerQueenMoves.add(new Movement(-1, -1, false, false, true));
        checkerQueenMoves.add(new Movement(1, 1, true, true, true));
        checkerQueenMoves.add(new Movement(1, -1, true, true, true));
        checkerQueenMoves.add(new Movement(-1, 1, true, true, true));
        checkerQueenMoves.add(new Movement(-1, -1, true, true, true));

        return new Piece(PieceName.QUEEN, checkerQueenMoves, color, 0);

    }
}
