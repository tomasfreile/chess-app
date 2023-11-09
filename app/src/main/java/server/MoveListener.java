package server;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import edu.austral.ingsis.clientserver.Server;
import org.jetbrains.annotations.NotNull;


public class MoveListener implements MessageListener<Move>{

    private final GameServer gameServer;

    public MoveListener(GameServer server) {
        this.gameServer = server;
    }
    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        gameServer.handleMove(message.getPayload());
    }
}
