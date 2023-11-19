package checkers.rules;

import commons.*;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.validator.GameValidator;

import java.util.List;


public class CheckersRules implements Rules {
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final List<GameValidator> gameMoveValidators;


    public CheckersRules(List<WinCondition> winConditions, List<StalemateCondition> stalemateConditions, List<GameValidator> gameMoveValidators) {
        this.winConditions = winConditions;
        this.stalemateConditions = stalemateConditions;
        this.gameMoveValidators = gameMoveValidators;
    }

    @Override
    public List<WinCondition> getWinConditions() {
        return winConditions;
    }

    @Override
    public List<StalemateCondition> getStalemateConditions() {
        return stalemateConditions;
    }

    @Override
    public List<GameValidator> getGameMoveValidators() {
        return null;
    }

    @Override
    public boolean checkWin(Board board, Color color) {
        for (WinCondition winCondition : winConditions) {
            if (winCondition.checkWin(board, color)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkGameMoveValidators(Board board, Color color, Tile start, Tile end) {
        for (GameValidator gameMoveValidator : gameMoveValidators) {
            if (!gameMoveValidator.isValid(start, end, board, color)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean checkStalemate(Board board, Color color) {
        for (StalemateCondition stalemateCondition : stalemateConditions) {
            if (stalemateCondition.isStalemate(board, color)) {
                return true;
            }
        }
        return false;
    }

}
