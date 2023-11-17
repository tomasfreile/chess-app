package chess;
import chess.factory.piece.PieceFactory;
import commons.*;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceMover;
import commons.piece.PieceName;
import commons.result.GameMoveResult;
import commons.result.GameOverResult;
import commons.result.SuccessfulMove;
import commons.result.UnsuccessfulMove;
import commons.rules.Rules;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessPieceMover implements PieceMover {
    @Override
    public GameMoveResult move(Tile from, Tile to, Piece piece, Game game) {
        Board board = game.getBoard();
        Rules rules = game.getRules();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = game.getCurrentPlayer();
        // Create a new list of positions by mapping the old positions with updated or unchanged tiles.
        Map<Tile, Piece> newPositions = board.getPositions();
        //remove from and add to
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

        if (rules.cannotMove(newBoard, piece.getColor(), from, to)) {
            return new UnsuccessfulMove("Cannot move into check", game);
        }

        // Switch to the next player and check for a win condition.
        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        // Create a new game with the updated board, players, and game over status.
        Game newGame = new Game(newBoard, player1, player2, rules, nextPlayer, this);

        if (gameOver) {
            return new GameOverResult(newGame);
        }

        return new SuccessfulMove(newGame);
    }

    @Override
    public GameMoveResult promote(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        Piece newPiece = PieceFactory.createPiece(PieceName.QUEEN, pieceColor);
        return move(from, to, newPiece, game);
    }

    @Override
    public Tile getCaptureTile(Tile from, Tile to) {
        return to;
    }

}
