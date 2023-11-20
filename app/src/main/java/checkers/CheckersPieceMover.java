package checkers;


import checkers.factory.piece.CheckersPieceFactory;
import checkers.validator.move.MustJumpCaptureValidator;
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
import commons.validator.AndValidator;
import commons.validator.moveValidators.CannotCaptureOwnPieceValidator;
import commons.validator.moveValidators.CannotCaptureValidator;
import commons.validator.moveValidators.DiagonalMoveValidator;
import commons.validator.moveValidators.ForwardMoveValidator;
import commons.validator.moveValidators.distance.DiagonalMoveDistanceValidator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CheckersPieceMover implements PieceMover {
    @Override
    public GameMoveResult move(Tile from, Tile to, Piece piece, Game game) {
        Board board = game.getBoard();
        Rules rules = game.getRules();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = game.getCurrentPlayer();

        if(!rules.checkGameMoveValidators(board, piece.getColor(), from, to)){
            return new UnsuccessfulMove("You have to capture a piece if you can", game);
        }

        if (isPromotion(to, piece, board)){
            piece = CheckersPieceFactory.createQueen(piece.getColor());
        }

        int rowDirection = from.getRow() < to.getRow() ? 1 : -1;
        int columnDirection = from.getColumn() < to.getColumn() ? 1 : -1;

        Map<Tile, Piece> newPositions = new HashMap<>(board.getPositions());
        Iterator<Tile> iterator = newPositions.keySet().iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            if (tile.getRow() == from.getRow() && tile.getColumn() == from.getColumn()) {
                iterator.remove();
                continue;
            }
            if (tile.getRow() == to.getRow() - rowDirection && tile.getColumn() == to.getColumn() - columnDirection) {
                // Remove the piece at the position before the 'to' tile
                iterator.remove();
            }
        }
        newPositions.put(to, new Piece(piece.getType(), piece.getMoves(), piece.getColor(), piece.getMoveCount() + 1));


        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        if (gameOver) {
            return new GameOverResult(new Game(newBoard, player1, player2, rules, nextPlayer, this));
        }

        if (new MustJumpCaptureValidator().isValid(from, to, board) && hasAvailableCapturesFromTile(to, newBoard)) {
            //if just captured a piece and can capture another piece, return the same player
            return new SuccessfulMove(new Game(newBoard, player1, player2, rules, currentPlayer, game.getPieceMover()));
        }

        return new SuccessfulMove(new Game(newBoard, player1, player2, rules, nextPlayer, game.getPieceMover()));
    }


    @Override
    public Tile getCapturedTile(Tile from, Tile to) {
        int rowDirection = Integer.compare(to.getRow(), from.getRow());
        int columnDirection = Integer.compare(to.getColumn(), from.getColumn());
        return new Tile(to.getRow() - rowDirection, to.getColumn() - columnDirection);
    }

    private boolean isPromotion(Tile to, Piece piece, Board board) {
        return  piece.getType().equals(PieceName.PAWN) && (to.getRow() == board.getHeight() - 1 && piece.getColor().equals(Color.WHITE) || to.getRow() == 0 && piece.getColor().equals(Color.BLACK));
    }

    private boolean hasAvailableCapturesFromTile(Tile to, Board newBoard) {
        for (int i = 0; i < newBoard.getHeight(); i++)
            for (int j = 0; j < newBoard.getWidth(); j++) {
                Tile destination = new Tile(i, j);
                //if piece can capture another piece, return true
                AndValidator diagonalTwoForward = new AndValidator(List.of(
                        new DiagonalMoveDistanceValidator(2),
                        new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator(),
                        new ForwardMoveValidator(), new MustJumpCaptureValidator(), new CannotCaptureValidator()));
                if (diagonalTwoForward.isValid(to, destination, newBoard)) {
                    return true;
                }
            }
        return false;
    }
}
