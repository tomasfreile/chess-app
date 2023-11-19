package commons.piece;

import commons.game.Game;
import commons.Tile;
import commons.result.GameMoveResult;

public interface PieceMover {
    GameMoveResult move(Tile from, Tile to, Piece p, Game game);
    Tile getCaptureTile(Tile from, Tile to);
}
