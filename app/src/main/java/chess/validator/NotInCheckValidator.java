package chess.validator;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.validator.GameValidator;
import commons.validator.Validator;

import java.util.Map;

public class NotInCheckValidator implements GameValidator {

    PieceName pieceName;

    public NotInCheckValidator(PieceName pieceName) {
        this.pieceName = pieceName;
    }
    @Override
    public boolean isValid(Tile from, Tile to, Board board, Color color) {
        return !isCheck(board, color);
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
    private Tile findKing(Board board, Color color) {
        //search the map for key with value king
        Map<Tile, Piece> positions = board.getPositions();
        for (Map.Entry<Tile, Piece> entry : positions.entrySet()) {
            if (entry.getValue().getType() == pieceName && entry.getValue().getColor() == color) {
                return entry.getKey();
            }
        }
        return null;
    }
}
