package checkers.validator.move;

import commons.Board;
import commons.Tile;
import commons.piece.Piece;
import commons.validator.Validator;

public class MustJumpCaptureValidator implements Validator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board) {
        int rowDirection = from.getRow() < to.getRow() ? 1 : -1;
        int columnDirection = from.getColumn() < to.getColumn() ? 1 : -1;

        Tile possibleCapture = new Tile(to.getRow() - rowDirection, to.getColumn() - columnDirection);
        if (board.isPositionOccupied(possibleCapture)){
            Piece piece = board.getPieceAtPosition(possibleCapture.getRow(), possibleCapture.getColumn());
            return piece.getColor() != board.getPieceAtPosition(from.getRow(),from.getColumn()).getColor();
        } else {
            return false;
        }

    }
}
