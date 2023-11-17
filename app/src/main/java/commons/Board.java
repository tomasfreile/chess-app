package commons;

import commons.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final int height;
    private final int width;

    private final Map<Tile, Piece> pieces;

    public Board(Map<Tile, Piece> pieces, int height, int width) {
        this.pieces = pieces;
        this.height = height;
        this.width = width;
    }

    public Map<Tile, Piece> getPositions() {
        return pieces;
    }

    public boolean isPositionOccupied(Tile position) {
        for (Tile tile : pieces.keySet()) {
            if (tile.getRow() == position.getRow() && tile.getColumn() == position.getColumn()) {
                return true;
            }
        }
        return false;
    }


    public Piece getPieceAtPosition(int row, int column) {
        for (Tile tile : pieces.keySet()) {
            if (tile.getRow() == row && tile.getColumn() == column) {
                return pieces.get(tile);
            }
        }
        return null;
    }


    public String printBoard() {
        StringBuilder board = new StringBuilder("   |  0   |  1   |  2   |  3   |  4   |  5   |  6   |  7   |\n");
        board.append("-------------------------------------------------------------\n");
        for (int i = 0; i < 8; i++) {
            board.append(" ").append(i).append(" |");
            for (int j = 0; j < 8; j++) {
                if (getPieceAtPosition(i, j) != null) {
                    Piece p = getPieceAtPosition(i, j);
                    String pieceString = p.getColor() == Color.WHITE ? "\u001B[32m" : "\u001B[31m"; // Green for white, Red for black
                    pieceString += p.getType().toString().substring(0, 4);
                    pieceString += "\u001B[0m"; // Reset text color
                    board.append(" ").append(pieceString).append(" |");
                } else {
                    board.append("      |");
                }
            }
            board.append("\n");
            board.append("-------------------------------------------------------------\n");
        }
        return board.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
