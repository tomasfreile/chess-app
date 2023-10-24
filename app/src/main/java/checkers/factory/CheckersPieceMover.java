package checkers.factory;


import checkers.factory.piece.CheckersQueenCreator;
import chess.factory.piece.QueenCreator;
import commons.*;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceMover;
import commons.rules.Rules;

import java.util.List;
import java.util.stream.Collectors;

public class CheckersPieceMover implements PieceMover {
    @Override
    public Game move(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        Board board = game.getBoard();
        Rules rules = game.getRules();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = game.getCurrentPlayer();

        List<Tile> newPositions = board.getPositions()
                .stream()
                .map(position
                        -> position == from ? new Tile(from.getRow(), from.getColumn())
                        : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), p.getColor(), p.getMoveCount()))
                        : Math.abs(from.getRow() - to.getRow()) == 2 && Math.abs(from.getColumn() - to.getColumn()) == 2 && position.getRow() == (from.getRow() + to.getRow()) / 2 && position.getColumn() == (from.getColumn() + to.getColumn()) / 2 ? new Tile(position.getRow(), position.getColumn())
                        : position)
                .collect(Collectors.toList());

        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        return new Game(newBoard, player1, player2, rules, nextPlayer, gameOver, this, game.getMoveVerifier());
    }

    @Override
    public Game promote(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        PieceCreator pieceCreator = new CheckersQueenCreator();
        Piece newPiece = pieceCreator.createPiece(pieceColor);
        return move(from, to, newPiece, game);
    }
}
