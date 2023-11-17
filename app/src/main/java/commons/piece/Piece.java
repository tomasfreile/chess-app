package commons.piece;

import commons.Color;
import commons.move.Movement;
import commons.validator.OrValidator;


public class Piece {

    private final PieceName pieceName;
    private final OrValidator movements;
    private final Color color;
    private final int moveCount;

    public Piece(PieceName pieceName, OrValidator movements, Color color, int moveCount) {
        this.pieceName = pieceName;
        this.movements = movements;
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

    public OrValidator getMoves() {
        return movements;
    }
}
