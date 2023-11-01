package checkers.factory;

import commons.*;
import commons.piece.Piece;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.validator.MoveVerifier;

import java.util.List;


public class CheckersRules implements Rules {

    private final List<Tile> startingPositions;
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final MoveHandler moveHandler = new MoveHandler();
    private final MoveVerifier moveVerifier = new CheckersMoveVerifier();


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
        if (isCapture(board, start, end)){
            return false;
        }
        return hasCapturesAvailable(board, color);
    }

    private boolean hasCapturesAvailable(Board board, Color color) {
        for (Tile p : board.getPositions()){
            if (!p.isEmpty() && p.getPiece().getColor() == color){
                for (Tile t : board.getPositions()){
                    if (moveHandler.validateMovement(p, t, board, moveVerifier)){
                        if (isCapture(board, p, t)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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

    private boolean isCapture(Board board, Tile from, Tile to) {
        Piece p = from.getPiece();
        int rowDirection = Integer.compare(to.getRow(), from.getRow());
        int columnDirection = Integer.compare(to.getColumn(), from.getColumn());

        Tile possibleCapture = board.getPosition(to.getRow() - rowDirection, to.getColumn() - columnDirection);

        return possibleCapture != null && !possibleCapture.isEmpty() && possibleCapture.getPiece().getColor() != p.getColor();

    }

}
