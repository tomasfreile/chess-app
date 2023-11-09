package commons.result;

import commons.Game;

public class UnsuccessfulMove implements GameMoveResult {
    String message;
    Game game;
    public UnsuccessfulMove(String message, Game game) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
