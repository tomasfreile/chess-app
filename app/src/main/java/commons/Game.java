package commons;

import commons.rules.Rules;

public interface Game {
    Game moveAndSwitchPlayer(Tile from, Tile to);
    Player getCurrentPlayer();
    boolean isGameOver();
    Rules getRules();
    Player getPlayer2();
    Player getPlayer1();
    Board getBoard();

}
