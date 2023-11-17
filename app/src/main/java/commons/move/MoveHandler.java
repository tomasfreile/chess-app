package commons.move;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.validator.MoveVerifier;

import java.util.List;

public class MoveHandler {

    public boolean validateMovement(Tile start, Tile end, Board board, MoveVerifier verifier) {
        if (isOutOfBounds(board, end.getRow(), end.getColumn())) {
            return false;
        }

        Piece p = board.getPieceAtPosition(start.getRow(), start.getColumn());
        Piece endPiece = board.getPieceAtPosition(end.getRow(), end.getColumn());

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

    public boolean isPromotion(Tile from, Tile to, Board board, MoveVerifier moveVerifier) {
        Piece p = board.getPieceAtPosition(from.getRow(), from.getColumn());
        Color pieceColor = p.getColor();
        return p.getType() == PieceName.PAWN && pieceColor == Color.WHITE && to.getRow() == 7 && validateMovement(from, to, board, moveVerifier) ||
                p.getType() == PieceName.PAWN && pieceColor == Color.BLACK && to.getRow() == 0 && validateMovement(from, to, board, moveVerifier);
    }

}
