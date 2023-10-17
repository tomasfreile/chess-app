import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Tile> positions;

    public Board() {
        positions = new ArrayList<Tile>();
    }

    public Board(List<Tile> positions) {
        this.positions = positions;
    }
    public Board addPositions(List<Tile> positions) { //immutable
        return new Board(positions);
    }

    public List<Tile> getPositions() {
        return positions;
    }

    public Piece getPieceAtPosition(int row, int column) {
        return getPosition(row, column).getPiece();
    }

    public Tile getPosition(int row, int column) {
        for (Tile p : positions) {
            if (p.getRow() == row && p.getColumn() == column) {
                return p;
            }
        }
        return null;
    }

    public boolean validateMovement(Tile start, Tile end, Rules rules) {

        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();

        Piece p = start.getPiece();
        Color color = p.getColor();

        List<Movement> moves = p.getMoves();

        if (endRow < 0 || endRow > 7 || endColumn < 0 || endColumn > 7) {
            return false;
        }

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

        if (start.getPiece().getMoves().isEmpty()) {
            return false;
        }
        if (startRow == endRow && startColumn == endColumn) {
            return false;
        }
        if (end.getPiece() != null && end.getPiece().getColor() == start.getPiece().getColor()) {
            return false;
        }

        // for each move check if it is valid
        return verifyPieceMovements(moves, start, end, rules);

    }

    private boolean verifyPieceMovements(List<Movement> moves, Tile start, Tile end, Rules rules){
        Piece p = start.getPiece();
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();
        int incrementRow = 0;
        int incrementColumn = 0;
        Color color = p.getColor();

        if (color == Color.WHITE){
            incrementRow = endRow - startRow;
            incrementColumn = endColumn - startColumn;
        }
        else {
            incrementRow = startRow - endRow;
            incrementColumn = startColumn - endColumn;
        }

        for (Movement m : moves){
            if (!m.canJump() && Math.abs(incrementColumn) != Math.abs(incrementRow) && m.getIncrementColumn() != 0 && m.getIncrementRow() != 0){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (!m.isTake() && end.getPiece() != null){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.isTake() && end.getPiece() == null){
                // This move is invalid, but keep looking for valid moves.
                continue;
            }
            if (m.canJump()){  //jump moves
                if (validateJumpMove(m, incrementRow, incrementColumn)) {
                    return true;
                }
            }
            else{ //non-jump moves
                if (validateNonJumpMove(m, incrementRow, incrementColumn, startRow, startColumn, endRow, endColumn)){
                    return true;
                }
            }
        }
        return rules.validateSpecialMovement(start, end, this);
    }

    private boolean checkMiddlePieces(int incrementRow, int incrementColumn, int startRow, int startColumn, int endRow, int endColumn){
        int row = startRow;
        int column = startColumn;
        int differenceRow = Math.abs(endRow - startRow);
        int differenceColumn = Math.abs(endColumn - startColumn);

        if (differenceColumn == 0 & differenceRow != 0){
            if (differenceRow <= 1){
                return true;
            }
            while (row != endRow){
                if (endRow < startRow){
                    row--;
                }
                else {
                    row++;
                }
                if (row != endRow && getPieceAtPosition(row, column) != null){
                    return false;
                }
            }
        }
        else if (differenceRow == 0 & differenceColumn != 0){
            if (differenceColumn <= 1){
                return true;
            }
            while (column != endColumn){
                if (endColumn < startColumn){
                    column--;
                }
                else {
                    column++;
                }
                if (column != endColumn && getPieceAtPosition(row, column) != null){
                    return false;
                }
            }
        }
        else {
            if (differenceRow <= 1 && differenceColumn <= 1){
                return true;
            }
        while (row != endRow && column != endColumn) {

            if (endRow < startRow) {
                row--;
            } else {
                row++;
            }
            if (endColumn < startColumn) {
                column--;
            } else {
                column++;
            }
            if (column != endColumn && row != endRow && getPieceAtPosition(row, column) != null) {
                return false;
            }
          }
        }
        return true;
    }

    private boolean validateJumpMove(Movement m, int incrementRow, int incrementColumn){
        if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0 && m.isLimitless()){
            return incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0;
        }
        else if (m.getIncrementRow() != 0 && m.isLimitless()){
            return incrementRow % m.getIncrementRow() == 0;
        }
        else if (m.getIncrementColumn() != 0 && m.isLimitless()){
            return incrementColumn % m.getIncrementColumn() == 0;
        }
        else if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0){
            return incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn();
        }
        else if (m.getIncrementRow() != 0){
            return incrementRow == m.getIncrementRow() && incrementColumn == 0;
        }
        else {
            return incrementColumn == m.getIncrementColumn() && incrementRow == 0;
        }
    }

    private boolean validateNonJumpMove(Movement m, int incrementRow, int incrementColumn, int startRow, int startColumn, int endRow, int endColumn){
       if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0 && m.isLimitless() && incrementRow != 0 && incrementColumn != 0){
           return incrementRow % m.getIncrementRow() == 0 && incrementColumn % m.getIncrementColumn() == 0 && checkMiddlePieces(m.getIncrementRow(), m.getIncrementColumn(), startRow, startColumn, endRow, endColumn);
       }
       else if (m.getIncrementRow() != 0 && m.isLimitless() && incrementRow != 0){
           return incrementRow % m.getIncrementRow() == 0 && incrementColumn == 0 && checkMiddlePieces(m.getIncrementRow(), m.getIncrementColumn(), startRow, startColumn, endRow, endColumn);
       }
       else if (m.getIncrementColumn() != 0 && m.isLimitless() && incrementColumn != 0){
           return incrementColumn % m.getIncrementColumn() == 0 && incrementRow == 0 && checkMiddlePieces(m.getIncrementRow(), m.getIncrementColumn(), startRow, startColumn, endRow, endColumn);
       }
       else if (m.getIncrementRow() != 0 && m.getIncrementColumn() != 0 && incrementRow != 0 && incrementColumn != 0 & !m.isLimitless()){
              return incrementRow == m.getIncrementRow() && incrementColumn == m.getIncrementColumn() && checkMiddlePieces(m.getIncrementRow(), m.getIncrementColumn(), startRow, startColumn, endRow, endColumn);
       }
       else if (m.getIncrementRow() != 0 && incrementRow != 0 && !m.isLimitless()){
           return incrementRow == m.getIncrementRow() && incrementColumn == 0 && checkMiddlePieces(m.getIncrementRow(), m.getIncrementColumn(), startRow, startColumn, endRow, endColumn);
       }
       else if (m.getIncrementColumn() != 0 && incrementColumn != 0 && !m.isLimitless()){
           return incrementColumn == m.getIncrementColumn() && incrementRow == 0 && checkMiddlePieces(m.getIncrementRow(), m.getIncrementColumn(), startRow, startColumn, endRow, endColumn);
       }
         else {
              return false;
         }
    }

    public String printBoard() {
        String board = "   |  0   |  1   |  2   |  3   |  4   |  5   |  6   |  7   |\n";
        board += "-------------------------------------------------------------\n";
        for (int i = 0; i < 8; i++) {
            board += " " + i + " |";
            for (int j = 0; j < 8; j++) {
                if (getPieceAtPosition(i, j) != null) {
                    Piece p = getPieceAtPosition(i, j);
                    String pieceString = p.getColor() == Color.WHITE ? "\u001B[32m" : "\u001B[31m"; // Green for white, Red for black
                    pieceString += p.getType().toString().substring(0, 4);
                    pieceString += "\u001B[0m"; // Reset text color
                    board += " " + pieceString + " |";
                } else {
                    board += "      |";
                }
            }
            board += "\n";
            board += "-------------------------------------------------------------\n";
        }
        return board;
    }

}
