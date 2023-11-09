package commons.result;

import commons.Game;

public class SuccessfulMove implements GameMoveResult{
    Game game;

    public SuccessfulMove(Game game) {
        this.game = game;
    }

    @Override
    public String getMessage() {
        return "Move successful";
    }

    @Override
    public Game getGame() {
        return game;
    }
}
