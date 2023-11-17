package checkers;


import checkers.validator.RequiredCaptureValidator;
import chess.factory.piece.PieceFactory;
import commons.*;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceMover;
import commons.piece.PieceName;
import commons.result.GameMoveResult;
import commons.result.GameOverResult;
import commons.result.SuccessfulMove;
import commons.result.UnsuccessfulMove;
import commons.rules.Rules;

import java.util.Iterator;
import java.util.Map;

public class CheckersPieceMover implements PieceMover {
    private final RequiredCaptureValidator requiredCaptureValidator = new RequiredCaptureValidator();
    @Override
    public GameMoveResult move(Tile from, Tile to, Piece p, Game game) {
        Board board = game.getBoard();
        Rules rules = game.getRules();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = game.getCurrentPlayer();

        if(rules.cannotMove(board, p.getColor(), from, to)){
            return new UnsuccessfulMove("You have to capture a piece if you can", game);
        }

            int rowDirection = Integer.compare(to.getRow(), from.getRow());
            int columnDirection = Integer.compare(to.getColumn(), from.getColumn());


        Map<Tile, Piece> newPositions = board.getPositions();

        Iterator<Tile> iterator = newPositions.keySet().iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            if (tile.getRow() == from.getRow() && tile.getColumn() == from.getColumn()) {
                iterator.remove();
            }
            if (tile.getRow() + rowDirection == to.getRow() && tile.getColumn() + columnDirection == to.getColumn()) {
                iterator.remove();
            }
        }
        newPositions.put(to, new Piece(p.getType(), p.getMoves(), p.getColor(), p.getMoveCount() + 1));

        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        if (gameOver) {
            return new GameOverResult(new Game(newBoard, player1, player2, rules, nextPlayer, this));
        }

        return canReCapture(from, to, board, newBoard) ? new SuccessfulMove(new Game(newBoard, player1, player2, rules, currentPlayer, this)) : new SuccessfulMove(new Game(newBoard, player1, player2, rules, nextPlayer, game.getPieceMover()));
    }

    private boolean canReCapture(Tile from, Tile to, Board board, Board newBoard) {
        return requiredCaptureValidator.isCapture(board, from, to) && requiredCaptureValidator.hasAvailableCapturesFromTile(newBoard, to);
    }

    @Override
    public GameMoveResult promote(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        Piece newPiece = PieceFactory.createPiece(PieceName.KING, pieceColor);
        return move(from, to, newPiece, game);
    }

    @Override
    public Tile getCaptureTile(Tile from, Tile to) {
        int rowDirection = Integer.compare(to.getRow(), from.getRow());
        int columnDirection = Integer.compare(to.getColumn(), from.getColumn());
        return new Tile(to.getRow() - rowDirection, to.getColumn() - columnDirection);
    }

}
