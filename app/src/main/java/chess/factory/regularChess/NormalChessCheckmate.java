package chess.factory.regularChess;

import chess.ChessMoveVerifier;
import commons.PieceName;
import commons.rules.WinCondition;
import commons.validator.MoveHandler;
import commons.Board;
import commons.Color;
import commons.Piece;
import commons.Tile;
import commons.validator.MoveVerifier;

import java.util.List;
import java.util.stream.Collectors;

public class NormalChessCheckmate implements WinCondition {

    MoveHandler moveValidator = new MoveHandler();
    MoveVerifier moveVerifier = new ChessMoveVerifier();
    @Override
    public boolean checkWin(Board board, Color color) {
        return isCheckmate(board, color);
    }

    private boolean isCheck(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        for (Tile p : board.getPositions()){
            assert kingPosition != null;
            if (!p.isEmpty() && moveValidator.validateMovement(p, kingPosition, board, moveVerifier)){
                return true;
            }
        }
        return false;
    }

    private boolean isCheckmate(Board board, Color color){
        if (isCheck(board, color)){
            return cannotMove(board, color);
        }
        return false;
    }

    private boolean cannotMove(Board board, Color color) {
        for (Tile p : board.getPositions()){
            if (!p.isEmpty() && p.getPiece().getColor() == color){
                for (Tile t : board.getPositions()){
                    if (moveValidator.validateMovement(p, t, board, moveVerifier)){
                        // Create a new board with the updated positions
                        List<Tile> newPositions = board.getPositions()
                                .stream()
                                .map(position -> position == p ? new Tile(p.getRow(), p.getColumn()) : position == t ? new Tile(t.getRow(), t.getColumn(), new Piece(p.getPiece().getType(), p.getPiece().getMoves(), p.getPiece().getColor(), p.getPiece().getMoveCount()+1)) : position)
                                .collect(Collectors.toList());

                        Board newBoard = new Board(newPositions, board.getHeight(), board.getHeight());
                        if (!isCheck(newBoard, color)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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
