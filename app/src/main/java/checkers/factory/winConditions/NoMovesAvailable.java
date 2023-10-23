package checkers.factory.winConditions;

import commons.Board;
import commons.Color;
import commons.rules.WinCondition;

public class NoMovesAvailable implements WinCondition {
    @Override
    public boolean checkWin(Board board, Color color) {
        return false;
    }
}
