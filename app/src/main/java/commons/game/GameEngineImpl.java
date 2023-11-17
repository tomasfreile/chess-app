package commons.game;

import commons.Board;
import commons.Color;
import commons.Tile;
import commons.adapter.PieceTranslator;
import commons.result.GameMoveResult;
import commons.result.GameOverResult;
import commons.result.UnsuccessfulMove;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
        Tile from = new Tile(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1);
        Tile to = new Tile(move.getTo().getRow() - 1, move.getTo().getColumn() - 1);
        GameMoveResult result = game.moveAndSwitchPlayer(from, to);

        if (result instanceof UnsuccessfulMove) {
            return new InvalidMove(result.getMessage());
        } else if (result instanceof GameOverResult) {
            PlayerColor winner = ((GameOverResult) result).getWinner().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK;
            return new GameOver(winner);
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

        String pieceId = isPromotion(move, movedPiece, game.getBoard()) ? "queen" : movedPiece.getPieceId();

        return updateGameState(movedPiece, move, pieceId, result.getGame());
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(game.getBoard().getWidth(),game.getBoard().getHeight()), pieces, PlayerColor.WHITE);
    }

    private MoveResult updateGameState(ChessPiece movedPiece, @NotNull Move move, String movedPieceName, Game result) {
        ChessPiece updatedPiece = new ChessPiece(movedPiece.getId(), movedPiece.getColor(), move.getTo(), movedPieceName);
        pieces.add(updatedPiece);
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

    private static boolean isPromotion(Move move, ChessPiece movedPiece, Board board) {
        return  movedPiece.getPieceId().equals("pawn") && (move.getTo().getRow() == board.getHeight() && movedPiece.getColor().equals(PlayerColor.WHITE) || move.getTo().getRow() == 1 && movedPiece.getColor().equals(PlayerColor.BLACK));
    }

    private ChessPiece findCapturedPiece(@NotNull Move move) {
        Tile capturedTile = game.getPieceMover().getCaptureTile(new Tile(move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1), new Tile(move.getTo().getRow() - 1, move.getTo().getColumn() - 1));
        return findPiece(new Position(capturedTile.getRow() + 1, capturedTile.getColumn() + 1));
    }

}
