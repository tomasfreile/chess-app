package checkers.factory.winConditions;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.piece.Piece;
import commons.rules.WinCondition;

import java.util.Map;

public class AllPiecesCaptured implements WinCondition {
    @Override
    public boolean checkWin(Board board, Color color) {
        Map<Tile, Piece> pieces = board.getPositions();
        for (Piece piece : pieces.values()) {
            if (piece.getColor() == color) {
                return false;
            }
        }
        return true;
    }
}
