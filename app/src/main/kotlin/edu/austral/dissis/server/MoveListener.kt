package edu.austral.dissis.server

import edu.austral.dissis.chess.gui.Move
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class MoveListener (val server: Server) : MessageListener<Move> {
    override fun handleMessage(message: Message<Move>) {
        server.handleMove(message.payload)
    }
}