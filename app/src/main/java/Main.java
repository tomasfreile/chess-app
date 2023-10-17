import java.util.*;

public class Main {
    public static void main(String[] args) {
        //rook
        List<Movement> rookMoves = new ArrayList<Movement>();
        rookMoves.add(new Movement(1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, true, false, true));
        rookMoves.add(new Movement(0, -1, true, false, true));
        rookMoves.add(new Movement(-1, 0, true, false, true));
        rookMoves.add(new Movement(0, 1, false, false, true));
        rookMoves.add(new Movement(0, -1, false, false, true));
        rookMoves.add(new Movement(1, 0, false, false, true));
        rookMoves.add(new Movement(-1, 0, false, false, true));

        //bishop
        List<Movement> bishopMoves = new ArrayList<Movement>();
        bishopMoves.add(new Movement(1, 1, true, false, true));
        bishopMoves.add(new Movement(-1, -1, true, false, true));
        bishopMoves.add(new Movement(1, -1, true, false, true));
        bishopMoves.add(new Movement(-1, 1, true, false, true));
        bishopMoves.add(new Movement(1, 1, false, false, true));
        bishopMoves.add(new Movement(-1, -1, false, false, true));
        bishopMoves.add(new Movement(1, -1, false, false, true));
        bishopMoves.add(new Movement(-1, 1, false, false, true));

        //knight
        List<Movement> knightMoves = new ArrayList<Movement>();
        knightMoves.add(new Movement(1, 2, true, true, false));
        knightMoves.add(new Movement(2, 1, true, true, false));
        knightMoves.add(new Movement(-1, 2, true, true, false));
        knightMoves.add(new Movement(-2, 1, true, true, false));
        knightMoves.add(new Movement(1, -2, true, true, false));
        knightMoves.add(new Movement(2, -1, true, true, false));
        knightMoves.add(new Movement(-1, -2, true, true, false));
        knightMoves.add(new Movement(-2, -1, true, true, false));
        knightMoves.add(new Movement(1, 2, false, true, false));
        knightMoves.add(new Movement(2, 1, false, true, false));
        knightMoves.add(new Movement(-1, 2, false, true, false));
        knightMoves.add(new Movement(-2, 1, false, true, false));
        knightMoves.add(new Movement(1, -2, false, true, false));
        knightMoves.add(new Movement(2, -1, false, true, false));
        knightMoves.add(new Movement(-1, -2, false, true, false));
        knightMoves.add(new Movement(-2, -1, false, true, false));

        //queen
        List<Movement> queenMoves = new ArrayList<Movement>();
        queenMoves.addAll(rookMoves);
        queenMoves.addAll(bishopMoves);

        //king
        List<Movement> kingMoves = new ArrayList<Movement>();
        kingMoves.add(new Movement(1, 1, true, false, false));
        kingMoves.add(new Movement(-1, -1, true, false, false));
        kingMoves.add(new Movement(1, -1, true, false, false));
        kingMoves.add(new Movement(-1, 1, true, false, false));
        kingMoves.add(new Movement(0, 1, true, false, false));
        kingMoves.add(new Movement(0, -1, true, false, false));
        kingMoves.add(new Movement(1, 0, true, false, false));
        kingMoves.add(new Movement(-1, 0, true, false, false));
        kingMoves.add(new Movement(1, 1, false, false, false));
        kingMoves.add(new Movement(-1, -1, false, false, false));
        kingMoves.add(new Movement(1, -1, false, false, false));
        kingMoves.add(new Movement(-1, 1, false, false, false));
        kingMoves.add(new Movement(0, 1, false, false, false));
        kingMoves.add(new Movement(0, -1, false, false, false));
        kingMoves.add(new Movement(1, 0, false, false, false));
        kingMoves.add(new Movement(-1, 0, false, false, false));

        //pawn
        List<Movement> pawnMoves = new ArrayList<Movement>();
        pawnMoves.add(new Movement(1, 1, true, false, false));
        pawnMoves.add(new Movement(1, -1, true, false, false));
        pawnMoves.add(new Movement(1, 0, false, false, false));

        //rules
        Map<PieceName, List<Movement>> pieceMovements = new HashMap<PieceName, List<Movement>>();
        pieceMovements.put(PieceName.ROOK, rookMoves);
        pieceMovements.put(PieceName.BISHOP, bishopMoves);
        pieceMovements.put(PieceName.KNIGHT, knightMoves);
        pieceMovements.put(PieceName.QUEEN, queenMoves);
        pieceMovements.put(PieceName.KING, kingMoves);
        pieceMovements.put(PieceName.PAWN, pawnMoves);

        List<Tile> startingPositions = new ArrayList<Tile>();
        startingPositions.add(new Tile(0, 0, new Piece(PieceName.ROOK, rookMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 1, new Piece(PieceName.KNIGHT, knightMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 2, new Piece(PieceName.BISHOP, bishopMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 3, new Piece(PieceName.QUEEN, queenMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 4, new Piece(PieceName.KING, kingMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 5, new Piece(PieceName.BISHOP, bishopMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 6, new Piece(PieceName.KNIGHT, knightMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(0, 7, new Piece(PieceName.ROOK, rookMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 0, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 1, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 2, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 3, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 4, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 5, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 6, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(1, 7, new Piece(PieceName.PAWN, pawnMoves, Color.WHITE,0)));
        startingPositions.add(new Tile(6, 0, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 1, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 2, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 3, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 4, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 5, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 6, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(6, 7, new Piece(PieceName.PAWN, pawnMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 0, new Piece(PieceName.ROOK, rookMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 1, new Piece(PieceName.KNIGHT, knightMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 2, new Piece(PieceName.BISHOP, bishopMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 3, new Piece(PieceName.QUEEN, queenMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 4, new Piece(PieceName.KING, kingMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 5, new Piece(PieceName.BISHOP, bishopMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 6, new Piece(PieceName.KNIGHT, knightMoves, Color.BLACK,0)));
        startingPositions.add(new Tile(7, 7, new Piece(PieceName.ROOK, rookMoves, Color.BLACK,0)));
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                startingPositions.add(new Tile(i, j, null));
            }
        }

        Rules rules = new NormalChessRules(startingPositions, pieceMovements);

        Board board = new Board(rules.getStartingPositions());

        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        Game game = new Game(board, player1, player2, rules, player1, false);
        System.out.println(game.getBoard().printBoard());


        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            System.out.println(game.getCurrentPlayer().getName() + " turn");
            System.out.println("Enter the row of the piece you want to move");
            int fromRow = scanner.nextInt();
            System.out.println("Enter the column of the piece you want to move");
            int fromColumn = scanner.nextInt();
            System.out.println("Enter the row of the position you want to move to");
            int toRow = scanner.nextInt();
            System.out.println("Enter the column of the position you want to move to");
            int toColumn = scanner.nextInt();
            game = game.moveAndSwitchPlayer(game.getBoard().getPosition(fromRow, fromColumn), game.getBoard().getPosition(toRow, toColumn));
            System.out.println(game.getBoard().printBoard());
        }


    }

}