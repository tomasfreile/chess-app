package validator;

import chess.PieceName;
import commons.*;
import java.util.List;

public class MoveValidator {

    private final JumpMoveValidator jumpMoveValidator = new JumpMoveValidator();
    private final NonJumpMoveValidator nonJumpMoveValidator = new NonJumpMoveValidator();
    public boolean validateMovement(Tile start, Tile end, Board board) {
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
        return verifyPieceMovements(board, moves, start, end, incrementRow, incrementColumn);

    }

    public boolean validateSpecialMovement(Tile start, Tile end, Board board) {
        Piece p = start.getPiece();

        int incrementRow = (p.getColor() == Color.WHITE) ? end.getRow() - start.getRow() : start.getRow() - end.getRow();
        int incrementColumn = (p.getColor() == Color.WHITE) ? end.getColumn() - start.getColumn() : start.getColumn() - end.getColumn();

        //2 square pawn move
        if (checkPawnSpecialMove(board, p, incrementRow, incrementColumn, start.getRow(), start.getColumn(), end.getRow(), end.getColumn())) {
            return true;
        }

        return false;
    }

    private boolean verifyPieceMovements(Board board, List<Movement> moves, Tile start, Tile end, int incrementRow, int incrementColumn) {
        for (Movement m : moves){
            if (!m.canJump() && Math.abs(incrementColumn) != Math.abs(incrementRow) && (m.isLShaped() || m.isDiagonal())){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if ((m.isTake() && end.getPiece() == null) || (!m.isTake() && end.getPiece() != null)) {
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.canJump()){  //jump moves
                if (jumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                    return true;
                }
            }
            else{ //non-jump moves
               if (nonJumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                   return true;
               }
            }
        }
        return validateSpecialMovement(start, end, board);
    }

    private static boolean isIllegalMove(Tile start, Tile end, Piece p, Piece endPiece) {
        return start == end || p == null || p.getMoves().isEmpty() || (endPiece != null && endPiece.getColor() == p.getColor());
    }

    private static boolean isOutOfBounds(Board board, int endRow, int endColumn) {
        return endRow < 0 || endRow >= board.getHeight() || endColumn < 0 || endColumn >= board.getWidth();
    }

    private static boolean checkPawnSpecialMove(Board board, Piece p, int incrementRow, int incrementColumn, int startRow, int startColumn, int endRow, int endColumn) {
        if (p.getColor() == Color.WHITE)
            return p.getType() == PieceName.PAWN && incrementRow == 2 && incrementColumn == 0 && p.getMoveCount() == 0 && board.getPosition(startRow + 1, startColumn).isEmpty() && board.getPosition(endRow, endColumn).isEmpty();
        else if (p.getColor() == Color.BLACK) {
            return p.getType() == PieceName.PAWN && incrementRow == 2 && incrementColumn == 0 && p.getMoveCount() == 0 && board.getPosition(startRow - 1, startColumn).isEmpty() && board.getPosition(endRow, endColumn).isEmpty();
        }
        return false;
    }

}
