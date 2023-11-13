package commons.validator;

import commons.Board;
import commons.move.Movement;
import commons.Tile;

public class NonJumpMoveValidator implements Validator {
    @Override
    public boolean validate(Movement m, Board board, Tile start, Tile end, int incrementRow, int incrementColumn) {
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();

        if (!m.isLimitless()) {
            return (m.isDiagonal() && incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn()) ||
                    (m.isVertical() && incrementRow == m.getIncrementRow() && incrementColumn == 0) ||
                    (m.isHorizontal() && incrementRow == 0 && incrementColumn == m.getIncrementColumn()) &&
                            checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
        }

        if (m.isDiagonal() && incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0) {
            return checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
        }

        if (m.isVertical() && incrementRow % m.getIncrementRow() == 0 && incrementColumn == 0) {
            return checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
        }

        if (m.isHorizontal() && incrementColumn % m.getIncrementColumn() == 0 && incrementRow == 0) {
            return checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
        }

        return false;
    }


    private boolean checkMiddlePieces(Board board, int startRow, int startColumn, int endRow, int endColumn){
        int differenceRow = Math.abs(endRow - startRow);
        int differenceColumn = Math.abs(endColumn - startColumn);

        if (differenceRow <= 1 && differenceColumn <= 1) {
            return true;
        }

        int rowDirection = Integer.compare(endRow, startRow);
        int columnDirection = Integer.compare(endColumn, startColumn);

        int row = startRow + rowDirection;
        int column = startColumn + columnDirection;

        while (row != endRow || column != endColumn) {
            if (board.getPieceAtPosition(row, column) != null) {
                return false;
            }
            row += rowDirection;
            column += columnDirection;
        }

        return true;
    }
}
