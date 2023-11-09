package server;

import commons.Game;
import commons.Tile;
import commons.result.EndGame;
import commons.result.GameMoveResult;
import commons.result.UnsuccessfulMove;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;

public class GameServer{

    private Game game;
    private final Server server;

    public GameServer(Game game, Server server) {
        this.game = game;
        this.server = server;
    }
    public void handleMove(Move move) {
        Tile from = game.getBoard().getPosition(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1);
        Tile to = game.getBoard().getPosition(move.getTo().getRow() - 1, move.getTo().getColumn() - 1);
        GameMoveResult result = game.moveAndSwitchPlayer(from, to);
        if (result instanceof UnsuccessfulMove) {
            server.broadcast(new Message<>("invalid", result.getMessage()));
        } else if (result instanceof EndGame) {
            server.broadcast(new Message<>("end", result.getMessage()));
        } else {
            server.broadcast(new Message<>("move", result.getMessage()));

        }

    }


}
