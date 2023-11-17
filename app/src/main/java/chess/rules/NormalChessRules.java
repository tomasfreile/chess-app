package chess.rules;

import chess.ChessMoveVerifier;
import commons.*;
import commons.move.MoveHandler;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.validator.MoveVerifier;

import java.util.List;
import java.util.Map;

public class NormalChessRules implements Rules {
    private final List<WinCondition> winConditions;
    private final List<StalemateCondition> stalemateConditions;
    private final MoveHandler moveValidator = new MoveHandler();
    private final MoveVerifier moveVerifier = new ChessMoveVerifier();
    public NormalChessRules(List<WinCondition> winConditions, List<StalemateCondition> stalemateConditions) {

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
        Map<Tile, Piece> positions = board.getPositions();
        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
            if (entry.getValue().getColor() != color) {
                if (moveValidator.validateMovement(entry.getKey(), kingPosition, board, moveVerifier)) {
                    return true;
                }
            }
        }
        return false;
    }
    private Tile findKing(Board board, Color color) {
        //search the map for key with value king
        Map<Tile, Piece> positions = board.getPositions();
        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
            if (entry.getValue().getType() == PieceName.KING && entry.getValue().getColor() == color) {
                return entry.getKey();
            }
        }
        return null;
    }


}
