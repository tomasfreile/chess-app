package validator;

import commons.Board;
import commons.Color;
import commons.Movement;
import commons.Tile;

public class JumpMoveValidator implements Validator {
    @Override
    public boolean validate(Movement m, Board board, Tile start, Tile end, int incrementRow, int incrementColumn) {
        if ((m.isLShaped() || m.isDiagonal()) && m.isLimitless()){
            return incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0;
        }
        else if (m.isVertical() && m.isLimitless()){
            return incrementRow % m.getIncrementRow() == 0;
        }
        else if (m.isHorizontal() && m.isLimitless()){
            return incrementColumn % m.getIncrementColumn() == 0;
        }
        else if (m.isLShaped() || m.isDiagonal()){
            return incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn();
        }
        else if (m.isVertical()){
            return incrementRow == m.getIncrementRow() && incrementColumn == 0;
        }
        else {
            return incrementColumn == m.getIncrementColumn() && incrementRow == 0;
        }
    }

}
