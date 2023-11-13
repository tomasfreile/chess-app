package commons.piece;

import commons.Color;
import commons.move.Movement;

import java.util.List;

public class Piece {

    private final PieceName pieceName;
    private final List<Movement> moves;
    private final Color color;
    private final int moveCount;

    public Piece(PieceName pieceName, List<Movement> moves, Color color, int moveCount) {
        this.pieceName = pieceName;
        this.moves = moves;
        this.color = color;
        this.moveCount = moveCount;
    }
    public PieceName getType() {
        return pieceName;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public List<Movement> getMoves() {
        return moves;
    }

}
