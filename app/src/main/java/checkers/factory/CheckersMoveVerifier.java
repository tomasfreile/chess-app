package checkers.factory;

import commons.Board;
import commons.Movement;
import commons.Tile;
import commons.validator.JumpMoveValidator;
import commons.validator.MoveVerifier;
import commons.validator.NonJumpMoveValidator;

import java.util.List;

public class CheckersMoveVerifier implements MoveVerifier {

    private final JumpMoveValidator jumpMoveValidator = new JumpMoveValidator();
    private final NonJumpMoveValidator nonJumpMoveValidator = new NonJumpMoveValidator();
    @Override
    public boolean verifyPieceMovements(Board board, List<Movement> moves, Tile start, Tile end, int incrementRow, int incrementColumn) {
        for (Movement m : moves) {
            if (!m.canJump() && Math.abs(incrementColumn) != Math.abs(incrementRow) && (m.isLShaped() || m.isDiagonal())){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (!end.isEmpty()){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.canJump()) {  //jump moves
                if (m.isLimitless()) { // Queen capture
                    if (!hasObstacles(board, start.getRow(), start.getColumn(), end.getRow(), end.getColumn())) {
                        return jumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn);
                    }
                }
                else { //normal capture
                    // Check if there is a piece being jumped over.
                    int jumpedRow = (start.getRow() + end.getRow()) / 2;
                    int jumpedColumn = (start.getColumn() + end.getColumn()) / 2;
                    Tile jumpedTile = board.getPosition(jumpedRow, jumpedColumn);

                    if (!jumpedTile.isEmpty() && jumpedTile.getPiece().getColor() != start.getPiece().getColor()) {
                        if (jumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)) {
                            return true;
                        }
                    }
                }
            } else{ //non-jump moves
                if (nonJumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasObstacles(Board board, int startRow, int startColumn, int endRow, int endColumn){
        int differenceRow = Math.abs(endRow - startRow);
        int differenceColumn = Math.abs(endColumn - startColumn);

        if (differenceRow <= 1 && differenceColumn <= 1) {
            return true;
        }

        int rowDirection = Integer.compare(endRow, startRow);
        int columnDirection = Integer.compare(endColumn, startColumn);

        int row = startRow + rowDirection;
        int column = startColumn + columnDirection;

        while (row != endRow - rowDirection || column != endColumn - columnDirection) {
            if (board.getPieceAtPosition(row, column) != null) {
                return false;
            }
            row += rowDirection;
            column += columnDirection;
        }

        return true;
    }

}
