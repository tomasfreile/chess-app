import java.util.List;
import java.util.Map;

public class NormalChessRules implements Rules{

    private final List<Tile> startingPositions;
    private final Map<PieceName, List<Movement>> pieceMovements;

    public NormalChessRules(List<Tile> startingPositions, Map<PieceName, List<Movement>> pieceMovements) {
        this.startingPositions = startingPositions;
        this.pieceMovements = pieceMovements;
    }
    @Override
    public List<Tile> getStartingPositions() {
        return startingPositions;
    }

    @Override
    public Map<PieceName, List<Movement>> getPieceMovements() {
        return pieceMovements;
    }

    @Override
    public boolean checkWin(Board board, Color color) {
        for (Tile p : board.getPositions()) {
            if (!p.isEmpty()) {
                if (p.getPiece().getType() == PieceName.KING && p.getPiece().getColor() == color) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean validateSpecialMovement(Tile origin, Tile destination, Board board) {
        int startRow = origin.getRow();
        int startColumn = origin.getColumn();
        int endRow = destination.getRow();
        int endColumn = destination.getColumn();

        Piece p = origin.getPiece();
        Color color = p.getColor();

        int incrementRow = 0;
        int incrementColumn = 0;

        if (color == Color.WHITE){
            incrementRow = endRow - startRow;
            incrementColumn = endColumn - startColumn;
        }
        else {
            incrementRow = startRow - endRow;
            incrementColumn = startColumn - endColumn;
        }
        //2 square pawn move
        if (checkPawnSpecialMove(board, p, incrementRow, incrementColumn, startRow, startColumn, endRow, endColumn)){
             return true;
        }

        //castle
//        if (checkCastle(board, p, incrementRow, incrementColumn, startRow, startColumn, endRow, endColumn)){
//            return true;
//        }
        return false;
    }

    private static boolean checkCastle(Board board, Piece p, int incrementRow, int incrementColumn, int startRow, int startColumn, int endRow, int endColumn) {
        if (p.getColor() == Color.WHITE && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == 2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn + 1).isEmpty() && board.getPosition(startRow, startColumn + 2).isEmpty() && board.getPosition(startRow, startColumn + 3).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn + 3).getPiece().getMoveCount() == 0) {
            return true;
        } else if (p.getColor() == Color.WHITE && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == -2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn - 1).isEmpty() && board.getPosition(startRow, startColumn - 2).isEmpty() && board.getPosition(startRow, startColumn - 3).isEmpty() && board.getPosition(startRow, startColumn - 4).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn - 4).getPiece().getMoveCount() == 0) {
            return true;
        } else if (p.getColor() == Color.BLACK && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == 2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn + 1).isEmpty() && board.getPosition(startRow, startColumn + 2).isEmpty() && board.getPosition(startRow, startColumn + 3).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn + 3).getPiece().getMoveCount() == 0) {
            return true;
        } else if (p.getColor() == Color.BLACK && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == -2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn - 1).isEmpty() && board.getPosition(startRow, startColumn - 2).isEmpty() && board.getPosition(startRow, startColumn - 3).isEmpty() && board.getPosition(startRow, startColumn - 4).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn - 4).getPiece().getMoveCount() == 0) {
            return true;
        }
        return false;
    }

    private static boolean checkPawnSpecialMove(Board board, Piece p, int incrementRow, int incrementColumn, int startRow, int startColumn, int endRow, int endColumn) {
        if (p.getColor() == Color.WHITE)
            return p.getType() == PieceName.PAWN && incrementRow == 2 && incrementColumn == 0 && p.getMoveCount() == 0 && board.getPosition(startRow + 1, startColumn).isEmpty() && board.getPosition(endRow, endColumn).isEmpty();
        else if (p.getColor() == Color.BLACK) {
            return p.getType() == PieceName.PAWN && incrementRow == 2 && incrementColumn == 0 && p.getMoveCount() == 0 && board.getPosition(startRow - 1, startColumn).isEmpty() && board.getPosition(endRow, endColumn).isEmpty();
        }
        return false;
    }

    private boolean isCheck(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        for (Tile p : board.getPositions()){
            assert kingPosition != null;
            if (board.validateMovement(p, kingPosition, this)){
                return true;
            }
        }
        return false;
    }

    private boolean isCheckmate(Board board, Color color){
        Tile kingPosition = findKing(board, color);
        for (Tile p : board.getPositions()){
            assert kingPosition != null;
            if (board.validateMovement(p, kingPosition, this)){
                return true;
            }
        }
        return false;
    }

    private Tile findKing(Board board, Color color) {
        for (Tile p : board.getPositions()) {
            if (!p.isEmpty()) {
                if (p.getPiece().getType() == PieceName.KING && p.getPiece().getColor() == color) {
                    return p;
                }
            }
        }
        return null;
    }


}
