import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Rules rules;
    private final Player currentPlayer;
    private final boolean gameOver;
    public Game(Board board, Player player1, Player player2, Rules rules, Player currentPlayer, boolean gameOver) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.rules = rules;
        this.currentPlayer = currentPlayer;
        this.gameOver = gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Rules getRules() {
        return rules;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game moveAndSwitchPlayer(Tile from, Tile to){
        if (to == null || from == null){
            System.out.println("Choose a position inside the board");
            return this;
        }
        if (from.isEmpty()){
            System.out.println("Choose a position with a piece");
            return this;
        }
        if (gameOver) {
            System.out.println("Game over");
            return this;
        }

        Piece p = from.getPiece();
        Color pieceColor = p.getColor();
        Color nextPlayerColor = currentPlayer == player1 ? player2.getColor() : player1.getColor();

        if (pieceColor == currentPlayer.getColor()) {
            if (isPromotion(p, pieceColor,from, to)) {
                return promote(from, to, pieceColor, nextPlayerColor);
            } else if (board.validateMovement(from, to, rules)) {
                return move(from, to, p, pieceColor, nextPlayerColor);
            } else {
                System.out.println("The move " + from.getRow() + "," + from.getColumn() + " to " + to.getRow() + "," + to.getColumn() + " is not valid");
                return this;
            }
        }
        else {
            System.out.println("Cannot move opponent's piece");
            return this;
        }
    }

    @NotNull
    private Game move(Tile from, Tile to, Piece p, Color pieceColor, Color nextPlayerColor) {
        // Create a new board with the updated positions
        List<Tile> newPositions = board.getPositions()
                .stream()
                .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), pieceColor, p.getMoveCount()+1)) : position)
                .collect(Collectors.toList());
        Board newBoard = new Board(newPositions);

        if (rules.isInCheck(newBoard, pieceColor)) {
            System.out.println("Cannot move into check");
            return this;
        }

        // Create a new game with the updated player and board
        Player newCurrentPlayer = currentPlayer == player1 ? player2 : player1;
        return new Game(newBoard, player1, player2, rules, newCurrentPlayer, rules.checkWin(newBoard, nextPlayerColor));
    }

    @NotNull
    private Game promote(Tile from, Tile to, Color pieceColor, Color nextPlayerColor) {
        // Create a new board with the updated positions
        List<Tile> newPositions = board.getPositions()
                .stream()
                .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(PieceName.QUEEN, rules.getPieceMovements().get(PieceName.QUEEN), pieceColor, 0)) : position)
                .collect(Collectors.toList());
        Board newBoard = new Board(newPositions);

        // Create a new game with the updated player and board
        Player newCurrentPlayer = currentPlayer == player1 ? player2 : player1;
        return new Game(newBoard, player1, player2, rules, newCurrentPlayer, rules.checkWin(newBoard, nextPlayerColor));
    }

    private boolean isPromotion(Piece p, Color pieceColor,Tile from, Tile to) {
        return p.getType() == PieceName.PAWN && pieceColor == Color.WHITE && to.getRow() == 7 && board.validateMovement(from, to, rules) ||
                p.getType() == PieceName.PAWN && pieceColor == Color.BLACK && to.getRow() == 0 && board.validateMovement(from, to, rules);
    }

//    private boolean isCastle(Piece p, Color pieceColor,Tile from, Tile to) {
//        int startRow = from.getRow();
//        int startColumn = from.getColumn();
//        int endRow = to.getRow();
//        int endColumn = to.getColumn();
//
//        int incrementRow = 0;
//        int incrementColumn = 0;
//
//        if (pieceColor == Color.WHITE){
//            incrementRow = endRow - startRow;
//            incrementColumn = endColumn - startColumn;
//        }
//        else {
//            incrementRow = startRow - endRow;
//            incrementColumn = startColumn - endColumn;
//        }
//
//        if (isWhiteShortCastle(p, pieceColor, incrementRow, incrementColumn, startRow, startColumn)) {
//            return true;
//        } else if (isWhiteLongCastle(p, pieceColor, incrementRow, incrementColumn, startRow, startColumn)) {
//            return true;
//        } else if (isBlackShortCastle(p, pieceColor, incrementRow, incrementColumn, startRow, startColumn)) {
//            return true;
//        } else if (isBlackLongCastle(p, pieceColor, incrementRow, incrementColumn, startRow, startColumn)) {
//            return true;
//        }
//        return false;
//    }

//    private boolean isBlackShortCastle(Piece p, Color pieceColor, int incrementRow, int incrementColumn, int startRow, int startColumn) {
//        return pieceColor == Color.BLACK && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == 2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn + 1).isEmpty() && board.getPosition(startRow, startColumn + 2).isEmpty() && board.getPosition(startRow, startColumn + 3).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn + 3).getPiece().getMoveCount() == 0;
//    }
//
//    private boolean isWhiteLongCastle(Piece p, Color pieceColor, int incrementRow, int incrementColumn, int startRow, int startColumn) {
//        return pieceColor == Color.WHITE && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == -2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn - 1).isEmpty() && board.getPosition(startRow, startColumn - 2).isEmpty() && board.getPosition(startRow, startColumn - 3).isEmpty() && board.getPosition(startRow, startColumn - 4).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn - 4).getPiece().getMoveCount() == 0;
//    }
//
//    private boolean isWhiteShortCastle(Piece p, Color pieceColor, int incrementRow, int incrementColumn, int startRow, int startColumn) {
//        return pieceColor == Color.WHITE && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == 2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn + 1).isEmpty() && board.getPosition(startRow, startColumn + 2).isEmpty() && board.getPosition(startRow, startColumn + 3).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn + 3).getPiece().getMoveCount() == 0;
//    }
//
//
//    private boolean isBlackLongCastle(Piece p, Color pieceColor, int incrementRow, int incrementColumn, int startRow, int startColumn) {
//        return pieceColor == Color.BLACK && p.getType() == PieceName.KING && incrementRow == 0 && incrementColumn == -2 && p.getMoveCount() == 0 && board.getPosition(startRow, startColumn - 1).isEmpty() && board.getPosition(startRow, startColumn - 2).isEmpty() && board.getPosition(startRow, startColumn - 3).isEmpty() && board.getPosition(startRow, startColumn - 4).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn - 4).getPiece().getMoveCount() == 0;
//    }
}
