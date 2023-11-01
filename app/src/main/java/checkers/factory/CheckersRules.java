package checkers.factory;

import checkers.validator.RequiredCaptureValidator;
import commons.*;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;

import java.util.List;


public class CheckersRules implements Rules {

    private final List<Tile> startingPositions;
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final RequiredCaptureValidator requiredCaptureValidator = new RequiredCaptureValidator();


    public CheckersRules(List<Tile> startingPositions, List<WinCondition> winConditions, List<StalemateCondition> stalemateConditions) {
        this.startingPositions = startingPositions;
        this.winConditions = winConditions;
        this.stalemateConditions = stalemateConditions;
    }
    @Override
    public List<Tile> getStartingPositions() {
        return startingPositions;
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

    @Override
    public boolean validateSpecialMovement(Tile start, Tile end, Board board) {
        return false;
    }


}
