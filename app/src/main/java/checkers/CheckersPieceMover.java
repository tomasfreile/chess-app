package checkers;


import checkers.factory.piece.CheckersQueenCreator;
import checkers.validator.RequiredCaptureValidator;
import commons.*;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceMover;
import commons.result.GameMoveResult;
import commons.result.SuccessfulMove;
import commons.result.UnsuccessfulMove;
import commons.rules.Rules;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Tile> newPositions = board.getPositions()
                .stream()
                .map(position
                        -> position == from ? new Tile(from.getRow(), from.getColumn())
                        : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), p.getColor(), p.getMoveCount()))
                        : position.getRow() + rowDirection == to.getRow() && position.getColumn() + columnDirection == to.getColumn() ? new Tile(position.getRow(), position.getColumn())
                        : position)
                .collect(Collectors.toList());

        Board newBoard = new Board(newPositions, board.getHeight(), board.getWidth());

        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        boolean gameOver = rules.checkWin(newBoard, nextPlayer.getColor());

        return canReCapture(from, to, board, newBoard) ? new SuccessfulMove(new Game(newBoard, player1, player2, rules, currentPlayer, gameOver, this, game.getMoveVerifier())) : new SuccessfulMove(new Game(newBoard, player1, player2, rules, nextPlayer, gameOver, game.getPieceMover(), game.getMoveVerifier()));
    }

    private boolean canReCapture(Tile from, Tile to, Board board, Board newBoard) {
        return requiredCaptureValidator.isCapture(board, from, to) && requiredCaptureValidator.hasAvailableCapturesFromTile(newBoard, newBoard.getPosition(to.getRow(), to.getColumn()));
    }

    @Override
    public GameMoveResult promote(Tile from, Tile to, Piece p, Game game) {
        Color pieceColor = p.getColor();
        PieceCreator pieceCreator = new CheckersQueenCreator();
        Piece newPiece = pieceCreator.createPiece(pieceColor);
        return move(from, to, newPiece, game);
    }

    @Override
    public Tile getCaptureTile(Tile from, Tile to) {
        int rowDirection = Integer.compare(to.getRow(), from.getRow());
        int columnDirection = Integer.compare(to.getColumn(), from.getColumn());
        return new Tile(to.getRow() - rowDirection, to.getColumn() - columnDirection);
    }

}
