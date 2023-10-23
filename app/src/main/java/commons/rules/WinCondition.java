package commons.rules;

import commons.Board;
import commons.Color;

public interface WinCondition {
    boolean checkWin(Board board, Color color);
}
