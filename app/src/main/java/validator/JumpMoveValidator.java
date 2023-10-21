package validator;

import commons.Board;
import commons.Color;
import commons.Movement;
import commons.Tile;

public class JumpMoveValidator implements Validator {
    @Override
    public boolean validate(Movement m, Board board, Tile start, Tile end, int incrementRow, int incrementColumn) {
        if (m.isLShaped() || m.isDiagonal()) {
            return (m.isLimitless() && incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0) ||
                    (!m.isLimitless() && incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn());
        } else if (m.isVertical()) {
            return (m.isLimitless() && incrementRow % m.getIncrementRow() == 0) || (!m.isLimitless() && incrementRow == m.getIncrementRow() && incrementColumn == 0);
        } else {
            return (m.isLimitless() && incrementColumn % m.getIncrementColumn() == 0) || (!m.isLimitless() && incrementRow == 0 && incrementColumn == m.getIncrementColumn());
        }
    }
}
