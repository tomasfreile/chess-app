package checkers.validator;

import commons.Board;
import commons.Color;
import commons.Tile;

public class RequiredCaptureValidator {


    public boolean hasAvailableCaptures(Board board, Color color) {
//        for (Tile p : board.getPositions()){
//            if (!p.isEmpty() && p.getPiece().getColor() == color){
//                for (Tile t : board.getPositions()){
//                    if (moveHandler.validateMovement(p, t, board, moveVerifier)){
//                        if (isCapture(board, p, t)){
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
        return false;
    }
    public boolean isCapture(Board board, Tile from, Tile to) {
//        Piece p = from.getPiece();
//        int rowDirection = Integer.compare(to.getRow(), from.getRow());
//        int columnDirection = Integer.compare(to.getColumn(), from.getColumn());
//
//        Tile possibleCapture = board.getPosition(to.getRow() - rowDirection, to.getColumn() - columnDirection);
//
//        return possibleCapture != null && !possibleCapture.isEmpty() && possibleCapture.getPiece().getColor() != p.getColor();
        return false;
    }

    public boolean hasAvailableCapturesFromTile(Board board, Tile from) {
//        Piece p = from.getPiece();
//        for (Tile t : board.getPositions()){
//            if (moveHandler.validateMovement(from, t, board, moveVerifier)){
//                if (isCapture(board, from, t)){
//                    return true;
//                }
//            }
//        }
//        return false;
        return false;
    }

}
