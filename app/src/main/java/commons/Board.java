package commons;

import commons.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final List<Tile> positions;
    private final int height;
    private final int width;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.positions = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                positions.add(new Tile(i, j));
            }
        }
    }

    public Board(List<Tile> positions, int height, int width) {
        this.positions = positions;
        this.height = height;
        this.width = width;
    }

    public Board replacePosition(Tile position){
        List<Tile> newPositions = positions
                .stream()
                .map(p -> p.getRow() == position.getRow() && p.getColumn() == position.getColumn() ? position : p)
                .collect(Collectors.toList());
        return new Board(newPositions, height, width);
    }

    public List<Tile> getPositions() {
        return positions;
    }

    public Piece getPieceAtPosition(int row, int column) {
        return getPosition(row, column).getPiece();
    }

    public Tile getPosition(int row, int column) {
        for (Tile p : positions) {
            if (p.getRow() == row && p.getColumn() == column) {
                return p;
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
