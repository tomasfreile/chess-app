package checkers;


import chess.factory.piece.PieceFactory;
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

import java.util.HashMap;
import java.util.Iterator;
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
            piece = PieceFactory.createPiece(PieceName.QUEEN, piece.getColor());
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

        return canReCapture(from, to, board, newBoard) ? new SuccessfulMove(new Game(newBoard, player1, player2, rules, currentPlayer, this)) : new SuccessfulMove(new Game(newBoard, player1, player2, rules, nextPlayer, game.getPieceMover()));
    }

    private boolean canReCapture(Tile from, Tile to, Board board, Board newBoard) {
        return false;
        //return requiredCaptureValidator.isCapture(board, from, to) && requiredCaptureValidator.hasAvailableCapturesFromTile(newBoard, to);
    }


    @Override
    public Tile getCaptureTile(Tile from, Tile to) {
        int rowDirection = Integer.compare(to.getRow(), from.getRow());
        int columnDirection = Integer.compare(to.getColumn(), from.getColumn());
        return new Tile(to.getRow() - rowDirection, to.getColumn() - columnDirection);
    }

    private boolean isPromotion(Tile to, Piece piece, Board board) {
        return  piece.getType().equals(PieceName.PAWN) && (to.getRow() == board.getHeight() - 1 && piece.getColor().equals(Color.WHITE) || to.getRow() == 1 && piece.getColor().equals(Color.BLACK));
    }

}
