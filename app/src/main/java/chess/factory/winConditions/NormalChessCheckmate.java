package chess.factory.winConditions;

import chess.ChessPieceMover;
import chess.validator.NotInCheckValidator;
import commons.piece.PieceMover;
import commons.piece.PieceName;
import commons.result.GameMoveResult;
import commons.rules.WinCondition;
import commons.Board;
import commons.Color;
import commons.piece.Piece;
import commons.Tile;
import commons.validator.GameValidator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class NormalChessCheckmate implements WinCondition {

    private final GameValidator notInCheckValidator = new NotInCheckValidator();

    @Override
    public boolean checkWin(Board board, Color color) {
        return isCheckmate(board, color);
    }


    private boolean isCheckmate(Board board, Color color) {
        if (!notInCheckValidator.isValid(null, null, board, color)) {
            return cannotMove(board, color);
        }
        return false;
    }

    private boolean cannotMove(Board board, Color color) {
        for (Tile tile : board.getPositions().keySet()){
            Piece piece = board.getPieceAtPosition(tile.getRow(), tile.getColumn());
            if (piece.getColor() == color) {
                for (int i = 0; i < board.getHeight(); i++){
                    for (int j = 0; j < board.getWidth(); j++) {
                        if (piece.getMoves().isValid(tile, new Tile(i, j), board)) {
                            Map<Tile, Piece> newPositions = new HashMap<>(board.getPositions());
                            Iterator<Tile> iterator = newPositions.keySet().iterator();
                            while (iterator.hasNext()) {
                                Tile tile1 = iterator.next();
                                if (tile1.getRow() == tile.getRow() && tile1.getColumn() == tile.getColumn()) {
                                    iterator.remove();
                                }
                                if (tile1.getRow() == i && tile1.getColumn() == j) {
                                    iterator.remove();
                                }
                            }
                            newPositions.put(new Tile(i, j), new Piece(piece.getType(),piece.getMoves(),piece.getColor(),piece.getMoveCount()+1));
                            Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

                            if (notInCheckValidator.isValid(null, null, newBoard, color)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
