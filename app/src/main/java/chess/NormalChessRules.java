package chess;

import commons.*;
import commons.MoveHandler;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.validator.MoveVerifier;

import java.util.List;

public class NormalChessRules implements Rules {
    private final List<Tile> startingPositions;
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final MoveHandler moveValidator = new MoveHandler();
    private final MoveVerifier moveVerifier = new ChessMoveVerifier();
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
    public boolean cannotMove(Board board, Color color, Tile start, Tile end) {
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

    @Override
    public boolean validateSpecialMovement(Tile start, Tile end, Board board) {
        Piece p = start.getPiece();

        int incrementRow = (p.getColor() == Color.WHITE) ? end.getRow() - start.getRow() : start.getRow() - end.getRow();
        int incrementColumn = (p.getColor() == Color.WHITE) ? end.getColumn() - start.getColumn() : start.getColumn() - end.getColumn();

        //2 square pawn move
        if (checkPawnSpecialMove(board, p, incrementRow, incrementColumn, start.getRow(), start.getColumn(), end.getRow(), end.getColumn())) {
            return true;
        }

        return false;
    }

    private static boolean checkPawnSpecialMove(Board board, Piece p, int incrementRow, int incrementColumn, int startRow, int startColumn, int endRow, int endColumn) {
        if (p.getColor() == Color.WHITE)
            return p.getType() == PieceName.PAWN && incrementRow == 2 && incrementColumn == 0 && p.getMoveCount() == 0 && board.getPosition(startRow + 1, startColumn).isEmpty() && board.getPosition(endRow, endColumn).isEmpty();
        else if (p.getColor() == Color.BLACK) {
            return p.getType() == PieceName.PAWN && incrementRow == 2 && incrementColumn == 0 && p.getMoveCount() == 0 && board.getPosition(startRow - 1, startColumn).isEmpty() && board.getPosition(endRow, endColumn).isEmpty();
        }
        return false;
    }

    private boolean isCheck(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        for (Tile p : board.getPositions()){
            assert kingPosition != null;
            if (!p.isEmpty() && moveValidator.validateMovement(p, kingPosition, board, moveVerifier)){
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
