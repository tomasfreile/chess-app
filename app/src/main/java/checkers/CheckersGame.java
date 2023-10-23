package checkers;

import chess.ChessMoveVerifier;
import commons.*;
import commons.rules.Rules;
import commons.validator.MoveHandler;
import commons.validator.MoveVerifier;

import java.util.List;
import java.util.stream.Collectors;

public class CheckersGame implements Game{

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;
    private Rules rules;
    private MoveHandler moveValidator;
    private boolean gameOver;

    private final MoveVerifier gameVerifier = new ChessMoveVerifier();

    public CheckersGame(Board board, Player player1, Player player2, Rules rules, Player currentPlayer, boolean gameOver) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = board;
        this.rules = rules;
        this.moveValidator = new MoveHandler();
        this.gameOver = gameOver;
    }

    @Override
    public Game moveAndSwitchPlayer(Tile from, Tile to) {
        if (moveValidator.validateMovement(from, to, board, gameVerifier)) {
            List<Tile> newPositions = board.getPositions()
                    .stream()
                    .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(from.getPiece().getType(), from.getPiece().getMoves(), from.getPiece().getColor(), from.getPiece().getMoveCount()+1)) : position)
                    .collect(Collectors.toList());

            Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());
            Player newPlayer = currentPlayer == player1 ? player2 : player1;
            return new CheckersGame(newBoard, player1, player2, rules, newPlayer, false);
        }
        return this;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Rules getRules() {
        return rules;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}

