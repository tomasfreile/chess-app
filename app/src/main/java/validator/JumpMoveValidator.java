package validator;

import commons.Board;
import commons.Color;
import commons.Movement;
import commons.Tile;

public class JumpMoveValidator implements Validator {
    @Override
    public boolean validate(Movement m, Board board, Tile start, Tile end) {
        int incrementRow = 0;
        int incrementColumn = 0;

        if (start.getPiece().getColor() == Color.WHITE){
            incrementRow = end.getRow() - start.getRow();
            incrementColumn = end.getColumn() - start.getColumn();
        }
        else {
            incrementRow = start.getRow() - end.getRow();
            incrementColumn = start.getColumn() - start.getRow();
        }

        if (m.isLimitless()){
            if (incrementsRowAndColumn(m)){
                return incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0;
            }
            else if (incrementsRow(m)){
                return incrementRow % m.getIncrementRow() == 0;
            }
            else if (incrementsColumn(m)){
                return incrementColumn % m.getIncrementColumn() == 0;
            }
            else {
                if (incrementsRowAndColumn(m)){
                    return incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn();
                }
                else if (incrementsRow(m)){
                    return incrementRow == m.getIncrementRow() && incrementColumn == 0;
                }
                else {
                    return incrementColumn == m.getIncrementColumn() && incrementRow == 0;
                }
            }
        }
        return false;
    }

    private static boolean incrementsColumn(Movement m) {
        return m.getIncrementColumn() != 0;
    }

    private static boolean incrementsRow(Movement m) {
        return m.getIncrementRow() != 0;
    }

    private static boolean incrementsRowAndColumn(Movement m) {
        return m.getIncrementRow() != 0 && m.getIncrementColumn() != 0;
    }
}
