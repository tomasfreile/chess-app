package checkers.factory;

import commons.Board;
import commons.Movement;
import commons.Tile;
import commons.validator.JumpMoveValidator;
import commons.validator.MoveVerifier;
import commons.validator.NonJumpMoveValidator;
import edu.austral.dissis.chess.gui.Move;

import java.util.List;

public class CheckersMoveVerifier implements MoveVerifier {

    private final JumpMoveValidator jumpMoveValidator = new JumpMoveValidator();
    private final NonJumpMoveValidator nonJumpMoveValidator = new NonJumpMoveValidator();
    @Override
    public boolean verifyPieceMovements(Board board, List<Movement> moves, Tile start, Tile end, int incrementRow, int incrementColumn) {
        // In checkers, you need to jump over a piece to capture it.
        for (Movement m : moves) {
            if (!end.isEmpty()) {
                continue; // If the destination tile is not empty, it's an invalid move.
            }
            if (m.getIncrementRow() != incrementRow || m.getIncrementColumn() != incrementColumn) {
                continue; // The movement doesn't match the allowed direction.
            }
            if (m.canJump()) {
                // Check if there is a piece being jumped over.
                int jumpedRow = (start.getRow() + end.getRow()) / 2;
                int jumpedColumn = (start.getColumn() + end.getColumn()) / 2;
                Tile jumpedTile = board.getPosition(jumpedRow, jumpedColumn);

                if (!jumpedTile.isEmpty() && jumpedTile.getPiece().getColor() != start.getPiece().getColor()) {
                    if (jumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)) {
                        return true;
                    }
                }
            } else {
                if (nonJumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

}
