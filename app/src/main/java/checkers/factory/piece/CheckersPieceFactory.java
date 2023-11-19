package checkers.factory.piece;


import checkers.validator.move.MustJumpCaptureValidator;
import checkers.validator.obstacles.NoObstaclesCheckersQueenValidator;
import commons.Color;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.validator.AndValidator;
import commons.validator.OrValidator;
import commons.validator.moveValidators.*;
import commons.validator.moveValidators.distance.DiagonalMoveDistanceValidator;
import commons.validator.moveValidators.obstacles.NoObstaclesDiagonalValidator;

import java.util.List;

public class CheckersPieceFactory {

    private static final AndValidator diagonalOneForward = new AndValidator(List.of(
            new DiagonalMoveDistanceValidator(1),
            new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new ForwardMoveValidator(), new CannotCaptureValidator()));

    private static final AndValidator diagonalTwoForward = new AndValidator(List.of(
            new DiagonalMoveDistanceValidator(2),
            new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new ForwardMoveValidator(), new MustJumpCaptureValidator(), new CannotCaptureValidator()));

    private static final AndValidator diagonalLimitless = new AndValidator(List.of(
            new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new NoObstaclesDiagonalValidator(), new CannotCaptureValidator()));

    private static final AndValidator diagonalLimitlessCapture = new AndValidator(List.of(
            new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new MustJumpCaptureValidator(), new CannotCaptureValidator(), new NoObstaclesCheckersQueenValidator()));

    public static Piece createPawn(Color color){
        return new Piece(PieceName.PAWN, new OrValidator(List.of(diagonalOneForward, diagonalTwoForward)), color, 0);
    }

    public static Piece createQueen(Color pieceColor) {
        return new Piece(PieceName.QUEEN, new OrValidator(List.of(diagonalLimitless, diagonalLimitlessCapture)), pieceColor, 0);
    }
}
