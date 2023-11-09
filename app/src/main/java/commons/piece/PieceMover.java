package commons.piece;

import commons.Game;
import commons.Tile;
import commons.piece.Piece;
import commons.result.GameMoveResult;

public interface PieceMover {
    GameMoveResult move(Tile from, Tile to, Piece p, Game game);
    GameMoveResult promote(Tile from, Tile to, Piece p, Game game);
    Tile getCaptureTile(Tile from, Tile to);
}
