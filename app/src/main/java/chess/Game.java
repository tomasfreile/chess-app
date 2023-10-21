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
            return printAndReturn("Choose a position inside the board");
        }

        if (from.isEmpty()) {
            return printAndReturn("Choose a position with a piece");
        }

        if (gameOver) {
            return printAndReturn("Game over");
        }

        Piece p = from.getPiece();
        Color pieceColor = p.getColor();

        if (pieceColor != currentPlayer.getColor()) {
            return printAndReturn("Cannot move opponent's piece");
        }

        if (isPromotion(p, pieceColor, from, to)) {
            return promote(from, to, pieceColor);
        }

        if (moveValidator.validateMovement(from, to, board)) {
            return move(from, to, p, pieceColor);
        }

        return printAndReturn("The move " + from.getRow() + "," + from.getColumn() + " to " + to.getRow() + "," + to.getColumn() + " is not valid");
    }

    private Game printAndReturn(String message) {
        System.out.println(message);
        return this;
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
        // Create a new list of positions by mapping the old positions with updated or unchanged tiles.
        List<Tile> newPositions = board.getPositions().stream()
                .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) :
                        position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), pieceColor, p.getMoveCount() + 1)) :
                                position)
                .collect(Collectors.toList());

        // Create a new board with the updated positions.
        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        if (rules.isInCheck(newBoard, pieceColor)) {
            printAndReturn("Cannot move into check");
        }

        // Switch to the next player and check for a win condition.
        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        // Create a new game with the updated board, players, and game over status.
        return new Game(newBoard, player1, player2, rules, nextPlayer, gameOver);
    }

    private boolean isPromotion(Piece p, Color pieceColor, Tile from, Tile to) {
        return p.getType() == PieceName.PAWN && pieceColor == Color.WHITE && to.getRow() == 7 && moveValidator.validateMovement(from, to, board) ||
                p.getType() == PieceName.PAWN && pieceColor == Color.BLACK && to.getRow() == 0 && moveValidator.validateMovement(from, to, board);
    }
}



