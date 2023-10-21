package validator;

import chess.PieceName;
import commons.*;
import validator.JumpMoveValidator;
import validator.NonJumpMoveValidator;

import java.util.List;

public class MoveValidator {

    private final JumpMoveValidator jumpMoveValidator = new JumpMoveValidator();
    private final NonJumpMoveValidator nonJumpMoveValidator = new NonJumpMoveValidator();
    public boolean validateMovement(Tile start, Tile end, Board board) {

        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();

        Piece p = start.getPiece();
        Color color = p.getColor();

        List<Movement> moves = p.getMoves();

        if (endRow < 0 || endRow > board.getHeight()-1 || endColumn < 0 || endColumn > board.getWidth()-1) {
            return false;
        }

        int incrementRow = 0;
        int incrementColumn = 0;

        if (color == Color.WHITE){
            incrementRow = endRow - startRow;
            incrementColumn = endColumn - startColumn;
        }
        else {
            incrementRow = startRow - endRow;
            incrementColumn = startColumn - endColumn;
        }

        if (start.getPiece().getMoves().isEmpty()) {
            return false;
        }
        if (startRow == endRow && startColumn == endColumn) {
            return false;
        }
        if (end.getPiece() != null && end.getPiece().getColor() == start.getPiece().getColor()) {
            return false;
        }

        // for each move check if it is valid
        return verifyPieceMovements(board, moves, start, end, incrementRow, incrementColumn);

    }
    private boolean verifyPieceMovements(Board board, List<Movement> moves, Tile start, Tile end, int incrementRow, int incrementColumn) {
        for (Movement m : moves){
            if (!m.canJump() && Math.abs(incrementColumn) != Math.abs(incrementRow) && m.getIncrementColumn() != 0 && m.getIncrementRow() != 0){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (!m.isTake() && end.getPiece() != null){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.isTake() && end.getPiece() == null){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.canJump()){  //jump moves
                if (jumpMoveValidator.validate(m, board, start, end)){
                    return true;
                }
                else{
                    continue;
                }
            }
            else{ //non-jump moves
               if (nonJumpMoveValidator.validate(m, board, start, end)){
                   return true;
               }
               else{
                   continue;
               }
            }
        }
        return false;
    }
    public boolean validateSpecialMovement(Tile start, Tile end, Board board) {
        Piece p = start.getPiece();
        Color color = p.getColor();

        int incrementRow = 0;
        int incrementColumn = 0;

        if (color == Color.WHITE){
            incrementRow = end.getRow() - start.getRow();
            incrementColumn = end.getColumn() - start.getColumn();
        }
        else {
            incrementRow = start.getRow() - end.getRow();
            incrementColumn = start.getColumn() - end.getColumn();
        }
        //2 square pawn move
        if (checkPawnSpecialMove(board, p, incrementRow, incrementColumn, start.getRow(), start.getColumn(), end.getRow(), end.getColumn())) {
            return true;
        }

        return false;
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
