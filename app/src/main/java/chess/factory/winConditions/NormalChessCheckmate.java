package chess.factory.winConditions;

import chess.ChessPieceMover;
import commons.piece.PieceMover;
import commons.piece.PieceName;
import commons.result.GameMoveResult;
import commons.rules.WinCondition;
import commons.Board;
import commons.Color;
import commons.piece.Piece;
import commons.Tile;

import java.util.Map;
import java.util.stream.Collectors;

public class NormalChessCheckmate implements WinCondition {

    private final PieceMover pieceMover = new ChessPieceMover();

    @Override
    public boolean checkWin(Board board, Color color) {
        return isCheckmate(board, color);
    }

    private boolean isCheck(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        Map<Tile, Piece> positions = board.getPositions();
        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
            if (entry.getValue().getColor() != color) {
                if (entry.getValue().getMoves().isValid(entry.getKey(), kingPosition, board)) {
                    return true;
                }
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

    private boolean cannotMove(Board board, Color color){
        Map<Tile,Piece> pieces = board.getPositions();
        for (Map.Entry<Tile, Piece> entry : pieces.entrySet()) {
            if (entry.getValue().getColor() == color) {
                for (int row = 0; row < board.getHeight(); row++) {
                    for (int column = 0; column < board.getWidth(); column++) {
                        Tile start = new Tile(row, column);
                        Tile end = new Tile(row, column);
                        if (entry.getValue().getMoves().isValid(start, end, board)) {
                            Map<Tile, Piece> newPositions = pieces.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                            newPositions.remove(start);
                            newPositions.put(end, new Piece(PieceName.KING, entry.getValue().getMoves(), color, entry.getValue().getMoveCount() + 1));
                            Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());
                            if (!isCheck(newBoard, color)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private Tile findKing(Board board, Color color) {
        Map<Tile, Piece> positions = board.getPositions();
        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
            if (entry.getValue().getType() == PieceName.KING && entry.getValue().getColor() == color) {
                return entry.getKey();
            }
        }
        return null;
    }
}
