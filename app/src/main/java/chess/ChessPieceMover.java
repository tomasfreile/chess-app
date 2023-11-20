package chess;
import chess.factory.piece.PieceFactory;
import commons.*;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceMover;
import commons.piece.PieceName;
import commons.result.GameMoveResult;
import commons.result.GameOverResult;
import commons.result.SuccessfulMove;
import commons.result.UnsuccessfulMove;
import commons.rules.Rules;
import edu.austral.dissis.chess.gui.Move;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChessPieceMover implements PieceMover {
    @Override
    public GameMoveResult move(Tile from, Tile to, Piece piece, Game game) {
        Board board = game.getBoard();
        Rules rules = game.getRules();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = game.getCurrentPlayer();

        if (isPromotion(to, piece, board)){
            piece = PieceFactory.createPiece(PieceName.QUEEN, piece.getColor());
        }

        // Create a new list of positions by mapping the old positions with updated or unchanged tiles.
        Map<Tile, Piece> newPositions = new HashMap<>(board.getPositions());
        Iterator<Tile> iterator = newPositions.keySet().iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            if (tile.getRow() == from.getRow() && tile.getColumn() == from.getColumn()) {
                iterator.remove();
            }
            if (tile.getRow() == to.getRow() && tile.getColumn() == to.getColumn()) {
                iterator.remove();
            }
        }

        newPositions.put(to, new Piece(piece.getType(),piece.getMoves(),piece.getColor(),piece.getMoveCount()+1));

        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        if (!rules.checkGameMoveValidators(newBoard, piece.getColor(), from, to)) {
            return new UnsuccessfulMove("Cannot move into check", game);
        }

        // Switch to the next player and check for a win condition.
        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        // Create a new game
        Game newGame = new Game(newBoard, player1, player2, rules, nextPlayer, this);

        if (gameOver) {
            return new GameOverResult(newGame);
        }

        return new SuccessfulMove(newGame);
    }


    @Override
    public Tile getCapturedTile(Tile from, Tile to) {
        return to;
    }

    private boolean isPromotion(Tile to, Piece piece, Board board) {
        return  piece.getType().equals(PieceName.PAWN) && (to.getRow() == board.getHeight() - 1 && piece.getColor().equals(Color.WHITE) || to.getRow() == 0 && piece.getColor().equals(Color.BLACK));
    }

}
