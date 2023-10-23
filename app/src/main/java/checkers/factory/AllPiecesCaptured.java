package checkers.factory;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.rules.WinCondition;

public class AllPiecesCaptured implements WinCondition {
    @Override
    public boolean checkWin(Board board, Color color) {
        for (Tile tile : board.getPositions()) {
            if (tile.getPiece() != null && tile.getPiece().getColor() == color) {
                return false;
            }
        }
        return true;
    }
}
