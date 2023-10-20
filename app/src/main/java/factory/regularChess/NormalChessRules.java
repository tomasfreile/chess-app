package factory.regularChess;

import chess.PieceName;
import commons.Board;
import commons.Color;
import commons.Tile;
import rules.Rules;
import rules.StalemateCondition;
import rules.WinCondition;
import validator.MoveValidator;

import java.util.List;

public class NormalChessRules implements Rules {
    private final List<Tile> startingPositions;
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final MoveValidator moveValidator = new MoveValidator();
    public NormalChessRules(List<Tile> startingPositions, List<WinCondition> winConditions, List<StalemateCondition> stalemateConditions) {
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
    public boolean isInCheck(Board board, Color color) {
        return isCheck(board, color);
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
    private boolean isCheck(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        for (Tile p : board.getPositions()){
            assert kingPosition != null;
            if (!p.isEmpty() && moveValidator.validateMovement(p, kingPosition, board)){
                return true;
            }
        }
        return false;
    }
    private Tile findKing(Board board, Color color) {
        for (Tile p : board.getPositions()) {
            if (!p.isEmpty()) {
                if (p.getPiece().getType() == PieceName.KING && p.getPiece().getColor() == color) {
                    return p;
                }
            }
        }
        return null;
    }


}
