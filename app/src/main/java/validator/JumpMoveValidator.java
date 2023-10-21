package validator;

import commons.Board;
import commons.Color;
import commons.Movement;
import commons.Tile;

public class JumpMoveValidator implements Validator {
    @Override
    public boolean validate(Movement m, Board board, Tile start, Tile end, int incrementRow, int incrementColumn) {
        if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0 && m.isLimitless()){
            return incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0;
        }
        else if (m.getIncrementRow() != 0 && m.isLimitless()){
            return incrementRow % m.getIncrementRow() == 0;
        }
        else if (m.getIncrementColumn() != 0 && m.isLimitless()){
            return incrementColumn % m.getIncrementColumn() == 0;
        }
        else if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0){
            return incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn();
        }
        else if (m.getIncrementRow() != 0){
            return incrementRow == m.getIncrementRow() && incrementColumn == 0;
        }
        else {
            return incrementColumn == m.getIncrementColumn() && incrementRow == 0;
        }
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
