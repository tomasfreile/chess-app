package chess;

import commons.Board;
import commons.move.Movement;
import commons.Tile;
import commons.validator.JumpMoveValidator;
import commons.validator.MoveVerifier;
import commons.validator.NonJumpMoveValidator;

import java.util.List;

public class ChessMoveVerifier implements MoveVerifier{

    private final JumpMoveValidator jumpMoveValidator;
    private final NonJumpMoveValidator nonJumpMoveValidator;

    public ChessMoveVerifier() {
        this.jumpMoveValidator = new JumpMoveValidator();
        this.nonJumpMoveValidator = new NonJumpMoveValidator();
    }
    @Override
    public boolean verifyPieceMovements(Board board, List<Movement> moves, Tile start, Tile end, int incrementRow, int incrementColumn) {
        for (Movement m : moves){
            if (!m.canJump() && Math.abs(incrementColumn) != Math.abs(incrementRow) && (m.isLShaped() || m.isDiagonal())){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if ((m.isCapture() && !board.isPositionOccupied(end)) || (!m.isCapture() && board.isPositionOccupied(end))) {
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.canJump()){  //jump moves
                if (jumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                    return true;
                }
            }
            else{ //non-jump moves
                if (nonJumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                    return true;
                }
            }
        }
        return false;
    }
}
