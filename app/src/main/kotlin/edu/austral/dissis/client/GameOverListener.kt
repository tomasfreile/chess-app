package edu.austral.dissis.client

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class GameOverListener (val client: ChessClient) : MessageListener<GameOver> {
    override fun handleMessage(message: Message<GameOver>) {
        client.handleGameOver(message)
    }

}