package chess;

import checkers.CheckersGame;
import checkers.factory.CheckersGameCreator;
import commons.Color;
import commons.Game;
import commons.Tile;
import edu.austral.dissis.chess.gui.*;
import chess.factory.regularChess.RegularGameCreator;
import org.jetbrains.annotations.NotNull;
import commons.Piece;

import java.util.ArrayList;
import java.util.List;

public class ChessGameEngineImpl implements GameEngine {

    Game game;
    List<ChessPiece> pieces;
    public ChessGameEngineImpl() {
        game = new RegularGameCreator().createGame();
        //game = new CheckersGameCreator().createGame();
        pieces = chessPieceList(game.getBoard().getPositions());
    }

    public List<ChessPiece> chessPieceList(List<Tile> positions){
        List<ChessPiece> pieces = new ArrayList<>();
        String id = "1";
        for (Tile tile : positions) {
            if (!tile.isEmpty()){
                Piece piece = tile.getPiece();
                ChessPiece newPiece = new ChessPiece(id,piece.getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK,new Position(tile.getRow()+1,tile.getColumn()+1),piece.getType().toString().toLowerCase());
                pieces.add(newPiece);
            }
            id = String.valueOf(Integer.parseInt(id)+1);
        }
        return pieces;
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
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
                return updateGameState(movedPiece, move, "queen", result);
            }
            else {
                return updateGameState(movedPiece, move, movedPiece.getPieceId(), result);
            }

        } else {
            return new InvalidMove("Try again");
        }
    }

    @NotNull
    private MoveResult updateGameState(ChessPiece movedPiece, @NotNull Move move, String movedPiece1, Game result) {
        ChessPiece updatedPiece = new ChessPiece(movedPiece.getId(), movedPiece.getColor(), move.getTo(), movedPiece1);
        pieces.add(updatedPiece);
        if (result.isGameOver()) {
            return new GameOver(result.getCurrentPlayer().equals(result.getPlayer2()) ? PlayerColor.WHITE : PlayerColor.BLACK);
        }
        return new NewGameState(pieces, result.getCurrentPlayer().getColor().equals(Color.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK);
    }

    private static boolean isPromotion(ChessPiece movedPiece) {
        return movedPiece.getPosition().getRow() == 7 && movedPiece.getColor().equals(PlayerColor.WHITE) && movedPiece.getPieceId().equals("pawn") || movedPiece.getPosition().getRow() == 2 && movedPiece.getColor().equals(PlayerColor.BLACK) && movedPiece.getPieceId().equals("pawn");
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(8,8), pieces,PlayerColor.WHITE);
    }


}
