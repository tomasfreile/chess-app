package checkers.factory.winConditions;

import checkers.CheckersMoveVerifier;
import commons.Board;
import commons.Color;
import commons.MoveHandler;
import commons.Tile;
import commons.rules.WinCondition;
import commons.validator.MoveVerifier;

public class NoMovesAvailable implements WinCondition {

    private final MoveHandler moveHandler = new MoveHandler();

    private final MoveVerifier moveVerifier = new CheckersMoveVerifier();

    @Override
    public boolean checkWin(Board board, Color color) {
        return cannotMove(board, color);
    }

    private boolean cannotMove(Board board, Color color) {
        for (Tile t: board.getPositions()){
            if (!t.isEmpty() && t.getPiece().getColor() == color){
                for (Tile t2: board.getPositions()){
                    if (moveHandler.validateMovement(t, t2, board, moveVerifier)){
                       return false;
                    }
                }
            }
        }
        return true;
    }
}
