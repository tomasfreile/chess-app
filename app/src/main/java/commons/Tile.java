package commons;

import commons.Piece;

public class Tile {

    private final int row;
    private final int column;
    private final Piece piece;

    public Tile(int row, int column) {
        this(row, column, null); // Initialize with no piece
    }

    public Tile(int row, int column, Piece piece) {
        this.row = row;
        this.column = column;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
