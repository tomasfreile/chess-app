package factory.regularChess;

import chess.PieceName;
import commons.Board;
import commons.Color;
import commons.Piece;
import commons.Tile;
import rules.StalemateCondition;
import validator.MoveValidator;

import java.util.List;
import java.util.stream.Collectors;

public class NormalChessStalemate implements StalemateCondition {

    MoveValidator moveValidator = new MoveValidator();

    @Override
    public boolean isStalemate(Board board, Color color) {
        if (!isCheck(board, color)){
            return cannotMove(board, color);
        }
        return false;
    }

    private boolean cannotMove(Board board, Color color) {
        for (Tile p : board.getPositions()){
            if (!p.isEmpty() && p.getPiece().getColor() == color){
                for (Tile t : board.getPositions()){
                    if (moveValidator.validateMovement(p, t, board)){
                        // Create a new board with the updated positions
                        List<Tile> newPositions = board.getPositions()
                                .stream()
                                .map(position -> position == p ? new Tile(p.getRow(), p.getColumn()) : position == t ? new Tile(t.getRow(), t.getColumn(), new Piece(p.getPiece().getType(), p.getPiece().getMoves(), p.getPiece().getColor(), p.getPiece().getMoveCount()+1)) : position)
                                .collect(Collectors.toList());

                        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());
                        if (!isCheck(newBoard, color)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isCheck(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        for (Tile p : board.getPositions()){
            assert kingPosition != null;
            if (!p.isEmpty() && moveValidator.validateMovement(p, kingPosition, board)){
                return true;
            }
        }
        return false;
    }

    private Tile findKing(Board board, Color color) {
        for (Tile p : board.getPositions()) {
            if (!p.isEmpty()) {
                if (p.getPiece().getType() == PieceName.KING && p.getPiece().getColor() == color) {
                    return p;
                }
            }
        }
        return null;
    }
}