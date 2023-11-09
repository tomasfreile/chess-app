package chess;

import chess.factory.piece.queen.QueenCreator;
import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceMover;
import commons.result.GameMoveResult;
import commons.result.SuccessfulMove;
import commons.result.UnsuccessfulMove;
import commons.rules.Rules;

import java.util.List;
import java.util.stream.Collectors;

public class ChessPieceMover implements PieceMover {
    @Override
    public GameMoveResult move(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        Board board = game.getBoard();
        Rules rules = game.getRules();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = game.getCurrentPlayer();
        // Create a new list of positions by mapping the old positions with updated or unchanged tiles.
        List<Tile> newPositions = board.getPositions().stream()
                .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) :
                        position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), pieceColor, p.getMoveCount() + 1)) :
                                position)
                .collect(Collectors.toList());

        // Create a new board with the updated positions.
        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        if (rules.cannotMove(newBoard, pieceColor, from, to)) {
            return new UnsuccessfulMove("Cannot move into check", game);
        }

        // Switch to the next player and check for a win condition.
        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        // Create a new game with the updated board, players, and game over status.
        Game newGame = new Game(newBoard, player1, player2, rules, nextPlayer, gameOver, this, game.getMoveVerifier());
        return new SuccessfulMove(newGame);
    }

    @Override
    public GameMoveResult promote(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        PieceCreator pieceCreator = new QueenCreator();
        Piece newPiece = pieceCreator.createPiece(pieceColor);
        return move(from, to, newPiece, game);
    }

    @Override
    public Tile getCaptureTile(Tile from, Tile to) {
        return to;
    }

}
