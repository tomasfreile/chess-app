package checkers;

import checkers.factory.CheckersGameCreator;
import commons.Color;
import commons.Game;
import commons.piece.PieceTranslator;
import commons.Tile;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CheckersGameEngineImpl implements GameEngine {

    Game game;
    List<ChessPiece> pieces;
    private PieceTranslator pieceTranslator = new PieceTranslator();

    public CheckersGameEngineImpl() {
        game = new CheckersGameCreator().createGame();
        pieces = pieceTranslator.translatePieceList(game.getBoard().getPositions());
    }
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Tile from = game.getBoard().getPosition(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1);
        Tile to = game.getBoard().getPosition(move.getTo().getRow() - 1, move.getTo().getColumn() - 1);

        Game result = game.moveAndSwitchPlayer(from, to);

        if (result.equals(game)) {
            return new InvalidMove("Try again");
        }

        game = result;

        ChessPiece movedPiece = findPiece(move.getFrom());
        if (movedPiece != null) {
            pieces.remove(movedPiece);
        }

        ChessPiece capturedPiece = findPiece(new Position((move.getFrom().getRow() + move.getTo().getRow()) / 2, (move.getFrom().getColumn() + move.getTo().getColumn()) / 2));
        if (capturedPiece != null) {
            pieces.remove(capturedPiece);
        }

        String pieceId = isPromotion(movedPiece) ? "queen" : movedPiece.getPieceId();

        assert movedPiece != null;
        return updateGameState(movedPiece, move, pieceId, result);
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(8,8), pieces, PlayerColor.WHITE);
    }

    @NotNull
    private MoveResult updateGameState(ChessPiece movedPiece, @NotNull Move move, String movedPieceName, Game result) {
        ChessPiece updatedPiece = new ChessPiece(movedPiece.getId(), movedPiece.getColor(), move.getTo(), movedPieceName);
        pieces.add(updatedPiece);
        if (result.isGameOver()) {
            return new GameOver(result.getCurrentPlayer().equals(result.getPlayer2()) ? PlayerColor.WHITE : PlayerColor.BLACK);
        }
        return new NewGameState(pieces, result.getCurrentPlayer().getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK);
    }

    private ChessPiece findPiece(Position position) {
        for (ChessPiece piece : pieces) {
            if (piece.getPosition().getRow() == position.getRow() && piece.getPosition().getColumn() == position.getColumn()) {
                return piece;
            }
        }
        return null;
    }

    private static boolean isPromotion(ChessPiece movedPiece) {
        return movedPiece.getPosition().getRow() == 7 && movedPiece.getColor().equals(PlayerColor.WHITE) && movedPiece.getPieceId().equals("pawn") || movedPiece.getPosition().getRow() == 2 && movedPiece.getColor().equals(PlayerColor.BLACK) && movedPiece.getPieceId().equals("pawn");
    }

}
