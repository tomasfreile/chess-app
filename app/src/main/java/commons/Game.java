package commons;

import chess.factory.ChessMoveVerifier;
import chess.factory.piece.QueenCreator;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceMover;
import commons.piece.PieceName;
import commons.validator.MoveVerifier;
import org.jetbrains.annotations.NotNull;
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

        if (isPromotion(p, pieceColor, from, to)) {
            return promote(from, to);
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
    

    @NotNull
    private Game promote(Tile from, Tile to) {
        Color pieceColor = from.getPiece().getColor();
        PieceCreator pieceCreator = new QueenCreator();
        Piece p = pieceCreator.createPiece(pieceColor);
        return pieceMover.move(from, to, p, this);
    }


    private boolean isPromotion(Piece p, Color pieceColor, Tile from, Tile to) {
        return p.getType() == PieceName.PAWN && pieceColor == Color.WHITE && to.getRow() == 7 && moveHandler.validateMovement(from, to, board, moveVerifier) ||
                p.getType() == PieceName.PAWN && pieceColor == Color.BLACK && to.getRow() == 0 && moveHandler.validateMovement(from, to, board, moveVerifier);
    }
}



