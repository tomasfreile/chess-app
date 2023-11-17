package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class CannotCaptureOwnPieceValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        return board.getPieceAtPosition(to.getRow(), to.getColumn()) == null ||
                board.getPieceAtPosition(from.getRow(), from.getColumn()).getColor() !=
                        board.getPieceAtPosition(to.getRow(), to.getColumn()).getColor();
    }
}
