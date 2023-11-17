package chess.factory.piece;

import commons.Color;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.validator.AndValidator;
import commons.validator.OrValidator;
import commons.validator.moveValidators.*;
import commons.validator.moveValidators.distance.DiagonalMoveDistanceValidator;
import commons.validator.moveValidators.distance.HorizontalMoveDistanceValidator;
import commons.validator.moveValidators.distance.VerticalMoveDistanceValidator;
import commons.validator.moveValidators.obstacles.NoObstaclesDiagonalValidator;
import commons.validator.moveValidators.obstacles.NoObstaclesHorizontalValidator;
import commons.validator.moveValidators.obstacles.NoObstaclesVerticalValidator;

import java.util.List;

public class PieceFactory {

    private static final AndValidator horizontalOne = new AndValidator(List.of(
            new HorizontalMoveDistanceValidator(1),
            new HorizontalMoveValidator(), new CannotCaptureOwnPieceValidator()));
    private static final AndValidator verticalOne = new AndValidator(List.of(
            new VerticalMoveDistanceValidator(1),
            new VerticalMoveValidator(), new CannotCaptureOwnPieceValidator()));
    private static final AndValidator diagonalOne = new AndValidator(List.of(
            new DiagonalMoveDistanceValidator(1),
            new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator()));

    private static final AndValidator horizontalLimitless = new AndValidator(List.of(
            new HorizontalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new NoObstaclesHorizontalValidator()));
    private static final AndValidator verticalLimitless = new AndValidator(List.of(
            new VerticalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new NoObstaclesVerticalValidator()));

    private static final AndValidator diagonalLimitless = new AndValidator(List.of(
            new DiagonalMoveValidator(), new CannotCaptureOwnPieceValidator(),
            new NoObstaclesDiagonalValidator()));

    private static final AndValidator diagonalOneMustCaptureForward = new AndValidator(List.of(
            new DiagonalMoveDistanceValidator(1),
            new DiagonalMoveValidator(),
            new MustCaptureValidator(),
            new ForwardMoveValidator(), new CannotCaptureOwnPieceValidator()));

    private static final AndValidator upOneNoCapture = new AndValidator(List.of(
            new VerticalMoveDistanceValidator(1),
            new VerticalMoveValidator(),
            new CannotCaptureValidator(),
            new ForwardMoveValidator()));

    private static final AndValidator upTwoNoCapture = new AndValidator(List.of(
            new VerticalMoveDistanceValidator(2),
            new VerticalMoveValidator(),
            new CannotCaptureValidator(),
            new ForwardMoveValidator(),
            new FirstMoveValidator()));

    private static final AndValidator knightMove = new AndValidator(List.of(
            new KnightMoveValidator(), new CannotCaptureOwnPieceValidator()));


    public static Piece createPiece(PieceName pieceType, Color color) {
        return switch (pieceType) {
            case PAWN -> {
                final OrValidator validator = new OrValidator(List.of(upOneNoCapture, diagonalOneMustCaptureForward, upTwoNoCapture));
                yield new Piece(pieceType, validator, color, 0);
            }
            case ROOK -> {
                final OrValidator validator = new OrValidator(List.of(horizontalLimitless, verticalLimitless));
                yield new Piece(pieceType, validator, color, 0);
            }
            case KNIGHT -> {
                final OrValidator validator = new OrValidator(List.of(knightMove));
                yield new Piece(pieceType, validator, color, 0);
            }
            case BISHOP -> {
                final OrValidator validator = new OrValidator(List.of(diagonalLimitless));
                yield new Piece(pieceType, validator, color, 0);
            }
            case QUEEN -> {
                final OrValidator validator = new OrValidator(List.of(diagonalLimitless, horizontalLimitless, verticalLimitless));
                yield new Piece(pieceType, validator, color, 0);
            }
            case KING -> {
                final OrValidator validator = new OrValidator(List.of(horizontalOne, verticalOne, diagonalOne));
                yield new Piece(pieceType, validator, color, 0);
            }
            case ARCHBISHOP -> null;
        };
    }
}
