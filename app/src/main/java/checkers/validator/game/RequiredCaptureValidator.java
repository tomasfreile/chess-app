package checkers.validator.game;

import checkers.validator.MustJumpCaptureValidator;
import commons.Board;
import commons.Color;
import commons.Tile;
import commons.piece.Piece;
import commons.validator.GameValidator;
import commons.validator.Validator;

public class RequiredCaptureValidator implements GameValidator {
    @Override
    public boolean isValid(Tile from, Tile to, Board board, Color color) {
        //if there is a capture available, the player must take it
        if (hasCapturesAvailable(board, color)){
            return new MustJumpCaptureValidator().isValid(from, to, board);
        } else {
            return true;
        }
    }


    private boolean hasCapturesAvailable(Board board, Color color){
        for (Tile tile : board.getPositions().keySet()){
            Piece piece = board.getPieceAtPosition(tile.getRow(), tile.getColumn());
            if (piece.getColor() == color){
                for (int i = 0; i < board.getHeight(); i++){
                    for (int j = 0; j < board.getWidth(); j++){
                        Tile to = new Tile(i, j);
                        if (piece.getMoves().isValid(tile, to, board)){
                            if (new MustJumpCaptureValidator().isValid(tile, to, board)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isCapture(Tile from, Tile to, Board board, Color color){
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
