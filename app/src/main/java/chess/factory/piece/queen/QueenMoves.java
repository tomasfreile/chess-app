package chess.factory.piece.queen;

import chess.factory.piece.bishop.BishopMoves;
import chess.factory.piece.rook.RookMoves;
import commons.move.Movement;

import java.util.List;

public class QueenMoves {
    List<Movement> getMoves() {
        List<Movement> rookMoves = new RookMoves().getMoves();
        List<Movement> bishopMoves = new BishopMoves().getMoves();
        rookMoves.addAll(bishopMoves);
        return rookMoves;
    }
}
