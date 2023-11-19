package commons.rules;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.validator.GameValidator;
import commons.validator.Validator;

import java.util.List;

public interface Rules {
    List<WinCondition> getWinConditions();
    List<StalemateCondition> getStalemateConditions();
    List<GameValidator> getGameMoveValidators();
    boolean checkWin(Board board, Color color);
    boolean checkGameMoveValidators(Board board, Color color, Tile start, Tile end);
    boolean checkStalemate(Board board, Color color);
}
