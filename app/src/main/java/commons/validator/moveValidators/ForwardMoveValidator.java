package commons.validator.moveValidators;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.validator.Validator;

public class ForwardMoveValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        Color color = board.getPieceAtPosition(from.getRow(), from.getColumn()).getColor();
        if (color == Color.WHITE) {
            return from.getRow() < to.getRow();
        } else {
            return from.getRow() > to.getRow();
        }
    }
}
