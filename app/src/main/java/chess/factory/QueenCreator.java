package chess.factory;

import commons.PieceName;
import commons.Color;
import commons.Movement;
import commons.Piece;

import java.util.ArrayList;
import java.util.List;

public class QueenCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> rookMoves = new ArrayList<>();
        rookMoves.add(new Movement(1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, true, false, true));
        rookMoves.add(new Movement(0, -1, true, false, true));
        rookMoves.add(new Movement(-1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, false, false, true));
        rookMoves.add(new Movement(0, -1, false, false, true));
        rookMoves.add(new Movement(1, 0, false, false, true));
        rookMoves.add(new Movement(-1, 0, false, false, true));
        List<Movement> bishopMoves = new ArrayList<>();
        bishopMoves.add(new Movement(1, 1, true, false, true));
        bishopMoves.add(new Movement(-1, -1, true, false, true));
        bishopMoves.add(new Movement(1, -1, true, false, true));
        bishopMoves.add(new Movement(-1, 1, true, false, true));
        bishopMoves.add(new Movement(1, 1, false, false, true));
        bishopMoves.add(new Movement(-1, -1, false, false, true));
        bishopMoves.add(new Movement(1, -1, false, false, true));
        bishopMoves.add(new Movement(-1, 1, false, false, true));
        List<Movement> queenMoves = new ArrayList<>();
        queenMoves.addAll(rookMoves);
        queenMoves.addAll(bishopMoves);
        return new Piece(PieceName.QUEEN, queenMoves, color, 0);
    }
}
