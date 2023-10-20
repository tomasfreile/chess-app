package rules;

import commons.Board;
import commons.Color;
import commons.Tile;

import java.util.List;

public interface Rules {
    List<Tile> getStartingPositions();
    List<WinCondition> getWinConditions();
    List<StalemateCondition> getStalemateConditions();
    boolean checkWin(Board board, Color color);
    boolean isInCheck(Board board, Color color);
    boolean isStalemate(Board board, Color color);
}
