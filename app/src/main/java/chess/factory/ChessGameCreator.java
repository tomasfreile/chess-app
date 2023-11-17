package chess.factory;

import chess.ChessPieceMover;
import chess.factory.piece.PieceFactory;
import chess.factory.stalemateConditions.NormalChessStalemate;
import chess.factory.winConditions.NormalChessCheckmate;
import chess.rules.NormalChessRules;
import commons.game.GameCreator;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceName;
import commons.rules.Rules;
import commons.rules.StalemateCondition;
import commons.rules.WinCondition;
import commons.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameCreator implements GameCreator {
    public ChessGameCreator() {
    }

    @Override
    public Game createGame(){


        Piece rookW = PieceFactory.createPiece(PieceName.ROOK, Color.WHITE);
        Piece knightW = PieceFactory.createPiece(PieceName.KNIGHT, Color.WHITE);
        Piece bishopW = PieceFactory.createPiece(PieceName.BISHOP, Color.WHITE);
        Piece queenW = PieceFactory.createPiece(PieceName.QUEEN, Color.WHITE);
        Piece kingW = PieceFactory.createPiece(PieceName.KING, Color.WHITE);
        Piece pawnW = PieceFactory.createPiece(PieceName.PAWN, Color.WHITE);
        Piece rookB = PieceFactory.createPiece(PieceName.ROOK, Color.BLACK);
        Piece knightB = PieceFactory.createPiece(PieceName.KNIGHT, Color.BLACK);
        Piece bishopB = PieceFactory.createPiece(PieceName.BISHOP, Color.BLACK);
        Piece queenB = PieceFactory.createPiece(PieceName.QUEEN, Color.BLACK);
        Piece kingB = PieceFactory.createPiece(PieceName.KING, Color.BLACK);
        Piece pawnB = PieceFactory.createPiece(PieceName.PAWN, Color.BLACK);



        Map<Tile,Piece> startingPositions = new HashMap<>();
        startingPositions.put(new Tile(0,0),rookW);
        startingPositions.put(new Tile(0,1),knightW);
        startingPositions.put(new Tile(0,2),bishopW);
        startingPositions.put(new Tile(0,3),queenW);
        startingPositions.put(new Tile(0,4),kingW);
        startingPositions.put(new Tile(0,5),bishopW);
        startingPositions.put(new Tile(0,6),knightW);
        startingPositions.put(new Tile(0,7),rookW);
        startingPositions.put(new Tile(1,0),pawnW);
        startingPositions.put(new Tile(1,1),pawnW);
        startingPositions.put(new Tile(1,2),pawnW);
        startingPositions.put(new Tile(1,3),pawnW);
        startingPositions.put(new Tile(1,4),pawnW);
        startingPositions.put(new Tile(1,5),pawnW);
        startingPositions.put(new Tile(1,6),pawnW);
        startingPositions.put(new Tile(1,7),pawnW);
        startingPositions.put(new Tile(6,0),pawnB);
        startingPositions.put(new Tile(6,1),pawnB);
        startingPositions.put(new Tile(6,2),pawnB);
        startingPositions.put(new Tile(6,3),pawnB);
        startingPositions.put(new Tile(6,4),pawnB);
        startingPositions.put(new Tile(6,5),pawnB);
        startingPositions.put(new Tile(6,6),pawnB);
        startingPositions.put(new Tile(6,7),pawnB);
        startingPositions.put(new Tile(7,0),rookB);
        startingPositions.put(new Tile(7,1),knightB);
        startingPositions.put(new Tile(7,2),bishopB);
        startingPositions.put(new Tile(7,3),queenB);
        startingPositions.put(new Tile(7,4),kingB);
        startingPositions.put(new Tile(7,5),bishopB);
        startingPositions.put(new Tile(7,6),knightB);
        startingPositions.put(new Tile(7,7),rookB);


        List<WinCondition> winConditions = new ArrayList<>();
        WinCondition winCondition = new NormalChessCheckmate();
        winConditions.add(winCondition);

        List<StalemateCondition> stalemateConditions = new ArrayList<>();
        StalemateCondition stalemateCondition = new NormalChessStalemate();
        stalemateConditions.add(stalemateCondition);

        Rules rules = new NormalChessRules(winConditions, stalemateConditions);

        Board board = new Board(startingPositions, 8, 8);


        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, new ChessPieceMover());
    }


}
