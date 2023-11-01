package commons.rules;

import commons.Board;
import commons.Color;
import commons.Tile;

import java.util.List;

public interface Rules {
    List<Tile> getStartingPositions();
    List<WinCondition> getWinConditions();
    List<StalemateCondition> getStalemateConditions();
    boolean checkWin(Board board, Color color);
    boolean cannotMove(Board board, Color color, Tile start, Tile end);
    boolean isStalemate(Board board, Color color);

    boolean validateSpecialMovement(Tile start, Tile end, Board board);
}
