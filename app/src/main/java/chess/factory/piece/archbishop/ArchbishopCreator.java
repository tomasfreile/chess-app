package chess.factory.piece.archbishop;

import chess.factory.piece.bishop.BishopMoves;
import chess.factory.piece.knight.KnightMoves;
import commons.Color;
import commons.move.Movement;
import commons.piece.Piece;
import commons.piece.PieceCreator;
import commons.piece.PieceName;

import java.util.ArrayList;
import java.util.List;

public class ArchbishopCreator implements PieceCreator {
    @Override
    public Piece createPiece(Color color) {
        List<Movement> bishopMoves = new BishopMoves().getMoves();
        List<Movement> knightMoves = new KnightMoves().getMoves();
        List <Movement> archbishopMoves = new ArrayList<>();
        archbishopMoves.addAll(bishopMoves);
        archbishopMoves.addAll(knightMoves);
        return new Piece(PieceName.ARCHBISHOP, archbishopMoves, color, 0);
    }
}
