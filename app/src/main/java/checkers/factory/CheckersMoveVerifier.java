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
        // in checkers you need to jump over a piece to capture it
        for (Movement m : moves){
            if (!end.isEmpty()){
                continue;
            }
            if (m.canJump()){
                if (jumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                    return true;
                }
            } else {
                if (nonJumpMoveValidator.validate(m, board, start, end, incrementRow, incrementColumn)){
                    return true;
                }
            }

        }
        return false;
    }
}
