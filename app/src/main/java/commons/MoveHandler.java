package commons;

import commons.piece.Piece;
import commons.validator.MoveVerifier;

import java.util.List;

public class MoveHandler {

    public boolean validateMovement(Tile start, Tile end, Board board, MoveVerifier verifier) {
        if (isOutOfBounds(board, end.getRow(), end.getColumn())) {
            return false;
        }

        Piece p = start.getPiece();
        Piece endPiece = end.getPiece();

        if (isIllegalMove(start, end, p, endPiece)) {
            return false;
        }

        int incrementRow = (p.getColor() == Color.WHITE) ? end.getRow() - start.getRow() : start.getRow() - end.getRow();
        int incrementColumn = (p.getColor() == Color.WHITE) ? end.getColumn() - start.getColumn() : start.getColumn() - end.getColumn();

        List<Movement> moves = p.getMoves();

        // for each move check if it is valid
        return verifier.verifyPieceMovements(board, moves, start, end, incrementRow, incrementColumn);

    }

    private static boolean isIllegalMove(Tile start, Tile end, Piece p, Piece endPiece) {
        return start == end || p == null || p.getMoves().isEmpty() || (endPiece != null && endPiece.getColor() == p.getColor());
    }

    private static boolean isOutOfBounds(Board board, int endRow, int endColumn) {
        return endRow < 0 || endRow >= board.getHeight() || endColumn < 0 || endColumn >= board.getWidth();
    }

}
