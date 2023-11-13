package commons.result;

import commons.Color;
import commons.game.Game;

public class GameOverResult implements GameMoveResult{

    private final Game game;
    public GameOverResult(Game game) {
        this.game = game;
    }
    @Override
    public String getMessage() {
        return "Game Over";
    }

    @Override
    public Game getGame() {
        return game;
    }

    public Color getWinner() {
        return game.getCurrentPlayer().getColor().equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
}
