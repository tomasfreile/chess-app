package commons.game;

import commons.Board;
import commons.Player;
import commons.Tile;
import commons.move.MoveHandler;
import commons.piece.Piece;
import commons.piece.PieceMover;
import commons.result.GameMoveResult;
import commons.result.UnsuccessfulMove;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public MoveVerifier getMoveVerifier() {
        return moveVerifier;
    }

    public PieceMover getPieceMover() {
        return pieceMover;
    }

    public GameMoveResult moveAndSwitchPlayer(Tile from, Tile to) {
        if (!board.isPositionOccupied(from)) {
            return new UnsuccessfulMove("Choose a position with a piece", this);
        }

        Piece p = board.getPieceAtPosition(from.getRow(), from.getColumn());

        if (p.getColor() != currentPlayer.getColor()) {
            return new UnsuccessfulMove("Choose a piece of your color", this);
        }

        if (moveHandler.isPromotion(from, to, board, moveVerifier)) {
            return pieceMover.promote(from, to, p, this);
        }

        if (moveHandler.validateMovement(from, to, board, moveVerifier)) {
            return pieceMover.move(from, to, p, this);
        }

        return new UnsuccessfulMove("Invalid move", this);
    }

}



