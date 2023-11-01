package commons;

import commons.piece.Piece;
import commons.piece.PieceMover;
import commons.validator.MoveVerifier;
import commons.rules.Rules;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Rules rules;
    private final Player currentPlayer;
    private final boolean gameOver;
    private final MoveHandler moveHandler;
    private final MoveVerifier moveVerifier;
    private final PieceMover pieceMover;

    public Game(Board board, Player player1, Player player2, Rules rules, Player currentPlayer, boolean gameOver, PieceMover pieceMover, MoveVerifier moveVerifier) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.rules = rules;
        this.currentPlayer = currentPlayer;
        this.gameOver = gameOver;
        this.moveHandler = new MoveHandler();
        this.moveVerifier = moveVerifier;
        this.pieceMover = pieceMover;
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

    public MoveHandler getMoveHandler() {
        return moveHandler;
    }

    public MoveVerifier getMoveVerifier() {
        return moveVerifier;
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

        if (moveHandler.isPromotion(from, to, board, moveVerifier)) {
            return pieceMover.promote(from, to, p, this);
        }

        if (moveHandler.validateMovement(from, to, board, moveVerifier) || rules.validateSpecialMovement(from, to, board)) {
            return pieceMover.move(from, to, p, this);
        }

        return printAndReturn("The move " + from.getRow() + "," + from.getColumn() + " to " + to.getRow() + "," + to.getColumn() + " is not valid");
    }

    private Game printAndReturn(String message) {
        System.out.println(message);
        return this;
    }

}



