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

    public Game moveAndSwitchPlayer(Tile from, Tile to) {
        if (to == null || from == null) {
            System.out.println("Choose a position inside the board");
            return this;
        }
        if (from.isEmpty()) {
            System.out.println("Choose a position with a piece");
            return this;
        }
        if (gameOver) {
            System.out.println("Game over");
            return this;
        }

        Piece p = from.getPiece();
        Color pieceColor = p.getColor();

        if (pieceColor == currentPlayer.getColor()) {
            if (isPromotion(p, pieceColor, from, to)) {
                return promote(from, to, pieceColor);
            } else if (board.validateMovement(from, to, rules)) {
                return move(from, to, p, pieceColor);
            } else {
                System.out.println("The move " + from.getRow() + "," + from.getColumn() + " to " + to.getRow() + "," + to.getColumn() + " is not valid");
                return this;
            }
        } else {
            System.out.println("Cannot move opponent's piece");
            return this;
        }
    }

    @NotNull
    private Game move(Tile from, Tile to, Piece p, Color pieceColor) {
        return updateGame(from, to, p, pieceColor);
    }

    @NotNull
    private Game promote(Tile from, Tile to, Color pieceColor) {
        return updateGame(from, to, new Piece(PieceName.QUEEN, rules.getPieceMovements().get(PieceName.QUEEN), pieceColor, 0), pieceColor);
    }


    @NotNull
    private Game updateGame(Tile from, Tile to, Piece p, Color pieceColor) {
        List<Tile> newPositions = board.getPositions()
                .stream()
                .map(position -> position == from ? new Tile(from.getRow(), from.getColumn()) : position == to ? new Tile(to.getRow(), to.getColumn(), new Piece(p.getType(), p.getMoves(), pieceColor, p.getMoveCount() + 1)) : position)
                .collect(Collectors.toList());
        Board newBoard = new Board(newPositions);

        if (rules.isInCheck(newBoard, pieceColor)) {
            System.out.println("Cannot move into check");
            return this;
        }

        // Create a new game with the updated player and board
        Player nextPlayer = currentPlayer == player1 ? player2 : player1;
        return new Game(newBoard, player1, player2, rules, nextPlayer, rules.checkWin(newBoard, nextPlayer.getColor()));
    }

    private boolean isPromotion(Piece p, Color pieceColor, Tile from, Tile to) {
        return p.getType() == PieceName.PAWN && pieceColor == Color.WHITE && to.getRow() == 7 && board.validateMovement(from, to, rules) ||
                p.getType() == PieceName.PAWN && pieceColor == Color.BLACK && to.getRow() == 0 && board.validateMovement(from, to, rules);
    }
}



    //    @NotNull
//    private Game castle(Tile from, Tile to, Piece p, Color pieceColor) {
//        //find rook position
//        int endRow = to.getRow();
//        int endColumn = to.getColumn();
//
//
//        //move king to "to" tile and rook to "to" -1 tile
//
//        Game movedKing = updateGame(from, to, p, pieceColor, false);
//
//        if (endColumn == 6) {
//            return movedKing.updateGame(movedKing.getBoard().getPosition(endRow, endColumn + 1), movedKing.getBoard().getPosition(endRow, endColumn - 1), movedKing.getBoard().getPosition(endRow, endColumn + 1).getPiece(), pieceColor, true);
//        }
//        else {
//            return movedKing.updateGame(movedKing.getBoard().getPosition(endRow, endColumn - 2), movedKing.getBoard().getPosition(endRow, endColumn + 1), movedKing.getBoard().getPosition(endRow, endColumn - 2).getPiece(), pieceColor, true);
//        }
//
//    }

//    private static boolean isCastle(Board board, Piece p, int incrementRow, int incrementColumn, int startRow, int startColumn) {
//        if (p.getType() != PieceName.KING) {
//            return false;
//        }
//        if (incrementRow != 0 || Math.abs(incrementColumn) != 2) {
//            return false;
//        }
//        if (p.getMoveCount() != 0) {
//            return false;
//        }
//        if (incrementColumn == 2) {
//            return board.getPosition(startRow, startColumn + 1).isEmpty() && board.getPosition(startRow, startColumn + 2).isEmpty() && board.getPosition(startRow, startColumn + 3).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn + 3).getPiece().getMoveCount() == 0;
//        }
//        if (incrementColumn == -2) {
//            return board.getPosition(startRow, startColumn - 1).isEmpty() && board.getPosition(startRow, startColumn - 2).isEmpty() && board.getPosition(startRow, startColumn - 3).isEmpty() && board.getPosition(startRow, startColumn - 4).getPiece().getType() == PieceName.ROOK && board.getPosition(startRow, startColumn - 4).getPiece().getMoveCount() == 0;
//        }
//
//
//        return false;
//    }


