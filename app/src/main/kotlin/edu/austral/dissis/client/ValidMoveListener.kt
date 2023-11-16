package edu.austral.dissis.client

import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ValidMoveListener (val client: ChessClient) : MessageListener<NewGameState> {
    override fun handleMessage(message: Message<NewGameState>) {
        client.handleNewGameState(message)
    }

}