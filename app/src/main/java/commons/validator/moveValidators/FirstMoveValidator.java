package commons.validator.moveValidators;

import commons.Board;
import commons.Tile;
import commons.validator.Validator;

public class FirstMoveValidator implements Validator {

    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        return board.getPieceAtPosition(from.getRow(), from.getColumn()).getMoveCount() == 0;
    }
}
