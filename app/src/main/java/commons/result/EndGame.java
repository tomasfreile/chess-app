package commons.result;

import commons.Color;
import commons.Game;
import commons.Player;

public class EndGame implements GameMoveResult{
    Game game;

    public EndGame(Game game) {
        this.game = game;
    }

    @Override
    public String getMessage() {
        Color winner = game.getCurrentPlayer().getColor();
        return "Game over. " + winner.toString() + " wins!";
    }

    @Override
    public Game getGame() {
        return game;
    }
}
