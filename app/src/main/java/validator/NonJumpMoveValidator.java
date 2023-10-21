package validator;

import commons.Board;
import commons.Color;
import commons.Movement;
import commons.Tile;

public class NonJumpMoveValidator implements Validator {
    @Override
    public boolean validate(Movement m, Board board, Tile start, Tile end, int incrementRow, int incrementColumn) {
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();


        if (m.isLimitless()){
            if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0 && incrementRow != 0 && incrementColumn != 0){
                return incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0 && checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
            }
            else if (m.getIncrementRow() != 0  && incrementRow != 0){
                return incrementRow % m.getIncrementRow() == 0 && incrementColumn == 0 && checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
            }
            else if (m.getIncrementColumn() != 0 && incrementColumn != 0){
                return incrementColumn % m.getIncrementColumn() == 0 && incrementRow == 0 && checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
            }
        }
        else {
            if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0 && incrementRow != 0 && incrementColumn != 0 & !m.isLimitless()){
                return incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn() && checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
            }
            else if (m.getIncrementRow() != 0 && incrementRow != 0 && !m.isLimitless()){
                return incrementRow == m.getIncrementRow() && incrementColumn == 0 && checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
            }
            else if (m.getIncrementColumn() != 0 && incrementColumn != 0 && !m.isLimitless()){
                return incrementColumn == m.getIncrementColumn() && incrementRow == 0 && checkMiddlePieces(board, startRow, startColumn, endRow, endColumn);
            }
        }
        return false;
    }

    private boolean checkMiddlePieces(Board board, int startRow, int startColumn, int endRow, int endColumn){
        int row = startRow;
        int column = startColumn;
        int differenceRow = Math.abs(endRow - startRow);
        int differenceColumn = Math.abs(endColumn - startColumn);

        if (differenceColumn == 0 & differenceRow != 0){
            if (differenceRow <= 1){
                return true;
            }
            while (row != endRow){
                if (endRow < startRow){
                    row--;
                }
                else {
                    row++;
                }
                if (row != endRow && board.getPieceAtPosition(row, column) != null){
                    return false;
                }
            }
        }
        else if (differenceRow == 0 & differenceColumn != 0){
            if (differenceColumn <= 1){
                return true;
            }
            while (column != endColumn){
                if (endColumn < startColumn){
                    column--;
                }
                else {
                    column++;
                }
                if (column != endColumn && board.getPieceAtPosition(row, column) != null){
                    return false;
                }
            }
        }
        else {
            if (differenceRow <= 1 && differenceColumn <= 1){
                return true;
            }
            while (row != endRow && column != endColumn) {

                if (endRow < startRow) {
                    row--;
                } else {
                    row++;
                }
                if (endColumn < startColumn) {
                    column--;
                } else {
                    column++;
                }
                if (column != endColumn && row != endRow && board.getPieceAtPosition(row, column) != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
