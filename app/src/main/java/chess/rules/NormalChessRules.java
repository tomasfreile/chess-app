package chess.rules;

import commons.*;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.validator.GameValidator;
import commons.validator.Validator;

import java.util.List;
import java.util.Map;

public class NormalChessRules implements Rules {
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;

    private final List<GameValidator> gameMoveValidators;

    public NormalChessRules(List<WinCondition> winConditions, List<StalemateCondition> stalemateConditions, List<GameValidator> gameMoveValidators) {
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
        return gameMoveValidators;
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
        for (GameValidator validator : gameMoveValidators) {
            if (!validator.isValid(start, end, board, color)) {
                return false;
            }
        }
        return true;
        //return isCheck(board, color);
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
