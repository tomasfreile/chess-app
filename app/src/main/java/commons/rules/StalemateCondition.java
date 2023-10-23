package commons.rules;

import commons.Board;
import commons.Color;

public interface StalemateCondition {
    boolean isStalemate(Board board, Color color);
}
