package factory;

import commons.Color;
import commons.Piece;

public interface PieceCreator {
    Piece createPiece(Color color);
}
