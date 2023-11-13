package commons.game;

import commons.Color;
import commons.Tile;
import commons.adapter.PieceTranslator;
import commons.result.GameMoveResult;
import commons.result.UnsuccessfulMove;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static java.sql.DriverManager.println;

public class GameEngineImpl implements GameEngine{
    Game game;
    List<ChessPiece> pieces;
    private PieceTranslator pieceTranslator = new PieceTranslator();

    public GameEngineImpl(Game game) {
        this.game = game;
        pieces = pieceTranslator.translatePieceList(game.getBoard().getPositions());
    }
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Tile from = game.getBoard().getPosition(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1);
        Tile to = game.getBoard().getPosition(move.getTo().getRow() - 1, move.getTo().getColumn() - 1);

        GameMoveResult result = game.moveAndSwitchPlayer(from, to);

        if (result instanceof UnsuccessfulMove) {
            return new InvalidMove(result.getMessage());
        }

        game = result.getGame();

        ChessPiece movedPiece = findPiece(move.getFrom());
        if (movedPiece != null) {
            pieces.remove(movedPiece);
        }

        assert movedPiece != null;
        ChessPiece capturedPiece = findCapturedPiece(move);

        if (capturedPiece != null) {
            pieces.remove(capturedPiece);
        }

        String pieceId = isPromotion(move, movedPiece) ? "queen" : movedPiece.getPieceId();

        return updateGameState(movedPiece, move, pieceId, result.getGame());
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(game.getBoard().getHeight(),game.getBoard().getWidth()), pieces, PlayerColor.WHITE);
    }

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

    private static boolean isPromotion(Move move, ChessPiece movedPiece) {
        return  movedPiece.getPieceId().equals("pawn") && (move.getTo().getRow() == 8 && movedPiece.getColor().equals(PlayerColor.WHITE) || move.getTo().getRow() == 1 && movedPiece.getColor().equals(PlayerColor.BLACK));
    }

    private ChessPiece findCapturedPiece(@NotNull Move move) {
        Tile capturedTile = game.getPieceMover().getCaptureTile(game.getBoard().getPosition(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1), game.getBoard().getPosition(move.getTo().getRow() - 1, move.getTo().getColumn() - 1));
        return findPiece(new Position(capturedTile.getRow() + 1, capturedTile.getColumn() + 1));
    }

}
