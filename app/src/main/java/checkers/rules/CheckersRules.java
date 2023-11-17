package checkers.rules;

import checkers.validator.RequiredCaptureValidator;
import commons.*;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;

import java.util.List;


public class CheckersRules implements Rules {
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final RequiredCaptureValidator requiredCaptureValidator = new RequiredCaptureValidator();


    public CheckersRules(List<WinCondition> winConditions, List<StalemateCondition> stalemateConditions) {

        this.winConditions = winConditions;
        this.stalemateConditions = stalemateConditions;
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
    public boolean checkWin(Board board, Color color) {
        for (WinCondition winCondition : winConditions) {
            if (winCondition.checkWin(board, color)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cannotMove(Board board, Color color, Tile start, Tile end) {
        if (requiredCaptureValidator.isCapture(board, start, end)){
            return false;
        }
        return requiredCaptureValidator.hasAvailableCaptures(board, color);
    }


    @Override
    public boolean isStalemate(Board board, Color color) {
        for (StalemateCondition stalemateCondition : stalemateConditions) {
            if (stalemateCondition.isStalemate(board, color)) {
                return true;
            }
        }
        return false;
    }


}
