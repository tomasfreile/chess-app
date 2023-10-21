package chess;

import commons.*;
import factory.PieceCreator;
import factory.QueenCreator;
import org.jetbrains.annotations.NotNull;
import rules.Rules;
import validator.MoveValidator;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Rules rules;
    private final Player currentPlayer;
    private final boolean gameOver;

    private final MoveValidator moveValidator = new MoveValidator();

    public Game(Board board, Player player1, Player player2, Rules rules, Player currentPlayer, boolean gameOver) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.rules = rules;
        this.currentPlayer = currentPlayer;
        this.gameOver = gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Rules getRules() {
        return rules;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game moveAndSwitchPlayer(Tile from, Tile to) {
        if (to == null || from == null) {
            System.out.println("Choose a position inside the board");
            return this;
        }
        if (from.isEmpty()) {
            System.out.println("Choose a position with a piece");
            return this;
        }
        if (gameOver) {
            System.out.println("chess.Game over");
            return this;
        }

        Piece p = from.getPiece();
        Color pieceColor = p.getColor();

        if (pieceColor == currentPlayer.getColor()) {
            if (isPromotion(p, pieceColor, from, to)) {
                return promote(from, to, pieceColor);
            } else if (moveValidator.validateMovement(from, to, board)) {
                return move(from, to, p, pieceColor);
            } else {
                System.out.println("The move " + from.getRow() + "," + from.getColumn() + " to " + to.getRow() + "," + to.getColumn() + " is not valid");
                return this;
            }
        } else {
            System.out.println("Cannot move opponent's piece");
            return this;
        }
    }

    @NotNull
    private Game move(Tile from, Tile to, Piece p, Color pieceColor) {
        return updateGame(from, to, p, pieceColor);
    }

    @NotNull
    private Game promote(Tile from, Tile to, Color pieceColor) {
        PieceCreator pieceCreator = new QueenCreator();
        Piece p = pieceCreator.createPiece(pieceColor);
        return updateGame(from, to, p, pieceColor);
    }

    @NotNull
    private Game updateGame(Tile from, Tile to, Piece p, Color pieceColor) {
        List<Tile> newPositions = board.getPositions()
                .stream()
                .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), pieceColor, p.getMoveCount() + 1)) : position)
                .collect(Collectors.toList());
        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        if (rules.isInCheck(newBoard, pieceColor)) {
            System.out.println("Cannot move into check");
            return this;
        }

        // Create a new game with the updated player and board
        Player nextPlayer = currentPlayer == player1 ? player2 : player1;
        return new Game(newBoard, player1, player2, rules, nextPlayer, rules.checkWin(newBoard, nextPlayer.getColor()));
    }

    private boolean isPromotion(Piece p, Color pieceColor, Tile from, Tile to) {
        return p.getType() == PieceName.PAWN && pieceColor == Color.WHITE && to.getRow() == 7 && moveValidator.validateMovement(from, to, board) ||
                p.getType() == PieceName.PAWN && pieceColor == Color.BLACK && to.getRow() == 0 && moveValidator.validateMovement(from, to, board);
    }
}



