package chess.factory;

import chess.ChessMoveVerifier;
import chess.ChessPieceMover;
import chess.factory.piece.archbishop.ArchbishopCreator;
import chess.factory.piece.bishop.BishopCreator;
import chess.factory.piece.king.KingCreator;
import chess.factory.piece.knight.KnightCreator;
import chess.factory.piece.pawn.PawnCreator;
import chess.factory.piece.queen.QueenCreator;
import chess.factory.piece.rook.RookCreator;
import chess.factory.stalemateConditions.NormalChessStalemate;
import chess.factory.winConditions.NormalChessCheckmate;
import chess.rules.NormalChessRules;
import commons.game.GameCreator;
import commons.game.Game;
import commons.piece.Piece;
import commons.piece.PieceCreator;
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

        //rook
        PieceCreator rookCreator = new RookCreator();
        Piece rookW = rookCreator.createPiece(Color.WHITE);
        Piece rookB = rookCreator.createPiece(Color.BLACK);

        //pawn
        PieceCreator pawnCreator = new PawnCreator();
        Piece pawnW = pawnCreator.createPiece(Color.WHITE);
        Piece pawnB = pawnCreator.createPiece(Color.BLACK);

        //knight
        PieceCreator knightCreator = new KnightCreator();
        Piece knightW = knightCreator.createPiece(Color.WHITE);
        Piece knightB = knightCreator.createPiece(Color.BLACK);

        //bishop
        PieceCreator bishopCreator = new BishopCreator();
        Piece bishopW = bishopCreator.createPiece(Color.WHITE);
        Piece bishopB = bishopCreator.createPiece(Color.BLACK);

        //queen
        PieceCreator queenCreator = new QueenCreator();
        Piece queenW = queenCreator.createPiece(Color.WHITE);
        Piece queenB = queenCreator.createPiece(Color.BLACK);

        //king
        PieceCreator kingCreator = new KingCreator();
        Piece kingW = kingCreator.createPiece(Color.WHITE);
        Piece kingB = kingCreator.createPiece(Color.BLACK);

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

        return new Game(board, player1, player2, rules, player1, false, new ChessPieceMover(), new ChessMoveVerifier());
    }


}
