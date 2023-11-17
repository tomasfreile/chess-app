package chess.factory.winConditions;

import commons.piece.PieceName;
import commons.rules.WinCondition;
import commons.Board;
import commons.Color;
import commons.piece.Piece;
import commons.Tile;

import java.util.Map;
import java.util.stream.Collectors;

public class NormalChessCheckmate implements WinCondition {


    @Override
    public boolean checkWin(Board board, Color color) {
        return isCheckmate(board, color);
    }

    private boolean isCheck(Board board, Color color){
//        Tile kingPosition = findKing(board, color);
//        Map<Tile, Piece> positions = board.getPositions();
//        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
//            if (entry.getValue().getColor() != color) {
//                if (moveValidator.validateMovement(entry.getKey(), kingPosition, board, moveVerifier)) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

    private boolean isCheckmate(Board board, Color color){
        if (isCheck(board, color)){
            return cannotMove(board, color);
        }
        return false;
    }

    private boolean cannotMove(Board board, Color color){
//        Map<Tile,Piece> pieces = board.getPositions();
//        for (Map.Entry<Tile, Piece> entry : pieces.entrySet()) {
//            if (entry.getValue().getColor() == color) {
//                for (int row = 0; row < board.getHeight(); row++) {
//                    for (int column = 0; column < board.getWidth(); column++) {
//                        Tile start = new Tile(row, column);
//                        Tile end = new Tile(row, column);
//                        if (moveValidator.validateMovement(start, end, board, moveVerifier)) {
//                            Map<Tile, Piece> newPositions = pieces.entrySet().stream()
//                                    .collect(Collectors.toMap(
//                                            e -> {
//                                                Tile tile = e.getKey();
//                                                if (tile.getRow() == start.getRow() && tile.getColumn() == start.getColumn()) {
//                                                    return new Tile(end.getRow(), end.getColumn());
//                                                }
//                                                return tile;
//                                            },
//                                            e -> {
//                                                Tile tile = e.getKey();
//                                                Piece piece = e.getValue();
//                                                if (tile.getRow() == start.getRow() && tile.getColumn() == start.getColumn()) {
//                                                    return new Piece(PieceName.PAWN, piece.getMoves(), color, piece.getMoveCount() + 1);
//                                                }
//                                                return piece;
//                                            }
//                                    ));
//                            Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());
//                            if (!isCheck(newBoard, color)){
//                                return false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
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
