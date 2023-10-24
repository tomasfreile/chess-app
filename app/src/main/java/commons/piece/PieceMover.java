package commons.piece;

import commons.Game;
import commons.Tile;
import commons.piece.Piece;

public interface PieceMover {
    Game move(Tile from, Tile to, Piece p, Game game);
}
