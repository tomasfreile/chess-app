import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        System.out.println("Checkmate:" + isCheckmate(board, color));
        return isCheckmate(board, color);
    }

    @Override
    public boolean isInCheck(Board board, Color color) {
        return isCheck(board, color);
    }

    @Override
    public boolean isStalemate(Board board, Color color) {
        if (!isCheck(board, color)){
            return cannotMove(board, color);
        }
        return false;
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
            if (!p.isEmpty() && board.validateMovement(p, kingPosition, this)){
                return true;
            }
        }
        return false;
    }

    private boolean isCheckmate(Board board, Color color){
            if (isCheck(board, color)){
                return cannotMove(board, color);
            }
            return false;
        }

    private boolean cannotMove(Board board, Color color) {
        for (Tile p : board.getPositions()){
            if (!p.isEmpty() && p.getPiece().getColor() == color){
                for (Tile t : board.getPositions()){
                    if (board.validateMovement(p, t, this)){
                        // Create a new board with the updated positions
                        List<Tile> newPositions = board.getPositions()
                                .stream()
                                .map(position -> position == p ? new Tile(p.getRow(), p.getColumn()) : position == t ? new Tile(t.getRow(), t.getColumn(), new Piece(p.getPiece().getType(), p.getPiece().getMoves(), p.getPiece().getColor(), p.getPiece().getMoveCount()+1)) : position)
                                .collect(Collectors.toList());

                        Board newBoard = new Board(newPositions);
                        if (!isCheck(newBoard, color)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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
