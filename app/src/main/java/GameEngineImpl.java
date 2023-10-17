import edu.austral.dissis.chess.gui.Position;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GameEngineImpl implements GameEngine {

    Game game;
    List<ChessPiece> pieces;
    public GameEngineImpl() {
        game = new GameCreator().createRegularGame();
        pieces = chessPieceList(game.getBoard().getPositions());
    }

    public List<ChessPiece> chessPieceList(List<Tile> positions){
        List<ChessPiece> pieces = new ArrayList<>();
        String id = "1";
        for (Tile positionB : positions) {
            if (!positionB.isEmpty()){
                Piece piece = positionB.getPiece();
                ChessPiece newPiece = new ChessPiece(id,piece.getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK,new Position(positionB.getRow()+1,positionB.getColumn()+1),piece.getType().toString().toLowerCase());
                pieces.add(newPiece);
            }
            id = String.valueOf(Integer.parseInt(id)+1);
        }
        return pieces;
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        if(game.isGameOver()){
            return new GameOver(game.getCurrentPlayer().equals(game.getPlayer2()) ? PlayerColor.WHITE : PlayerColor.BLACK);
        }
        if (!game.moveAndSwitchPlayer(game.getBoard().getPosition(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1), game.getBoard().getPosition(move.getTo().getRow() - 1, move.getTo().getColumn() - 1)).equals(game)) {
            Game result = game.moveAndSwitchPlayer(game.getBoard().getPosition(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1), game.getBoard().getPosition(move.getTo().getRow() - 1, move.getTo().getColumn() - 1));
            game = result;
            // Find and remove the piece from the old position
            ChessPiece movedPiece = null;
            ChessPiece capturedPiece = null;
            for (ChessPiece piece : pieces) {
                if (piece.getPosition().equals(move.getFrom())) {
                    movedPiece = piece;
                    break;
                }
            }
            // Find and remove the piece from the new position
            for (ChessPiece piece : pieces) {
                if (piece.getPosition().equals(move.getTo())) {
                    capturedPiece = piece;
                    break;
                }
            }
            if (movedPiece != null) {
                pieces.remove(movedPiece);
            }

            if (capturedPiece != null) {
                pieces.remove(capturedPiece);
            }

            assert movedPiece != null;

            if (isPromotion(movedPiece)) {
                ChessPiece updatedPiece = new ChessPiece(movedPiece.getId(), movedPiece.getColor(), move.getTo(), "queen");
                pieces.add(updatedPiece);
                return new NewGameState(pieces, result.getCurrentPlayer().getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK);
            }
            else {
                ChessPiece updatedPiece = new ChessPiece(movedPiece.getId(), movedPiece.getColor(), move.getTo(), movedPiece.getPieceId());
                pieces.add(updatedPiece);
                return new NewGameState(pieces, result.getCurrentPlayer().getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK);

            }

        } else {
            return new InvalidMove("Try again");
        }
    }

    private static boolean isPromotion(ChessPiece movedPiece) {
        return movedPiece.getPosition().getRow() == 7 && movedPiece.getColor().equals(PlayerColor.WHITE) && movedPiece.getPieceId().equals("pawn") || movedPiece.getPosition().getRow() == 0 && movedPiece.getColor().equals(PlayerColor.BLACK) && movedPiece.getPieceId().equals("pawn");
    }
    
    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(8,8), pieces,PlayerColor.WHITE);
    }
}
