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
import java.util.List;

public class ChessGameCreator implements GameCreator {
    public ChessGameCreator() {
    }

    @Override
    public Game createGame(){

        Board board = new Board(8,8);

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

        //archbishop
        PieceCreator archbishopCreator = new ArchbishopCreator();
        Piece archbishopW = archbishopCreator.createPiece(Color.WHITE);
        Piece archbishopB = archbishopCreator.createPiece(Color.BLACK);


        List<Tile> startingPositions = new ArrayList<>();
        startingPositions.add(new Tile(0, 0, rookW));
        startingPositions.add(new Tile(0, 1, knightW));
        startingPositions.add(new Tile(0, 2,bishopW));
        startingPositions.add(new Tile(0, 3,queenW));
        startingPositions.add(new Tile(0, 4, kingW));
        startingPositions.add(new Tile(0, 5, bishopW));
        startingPositions.add(new Tile(0, 6, knightW));
        startingPositions.add(new Tile(0, 7, rookW));
        startingPositions.add(new Tile(7, 0, rookB));
        startingPositions.add(new Tile(7, 1, knightB));
        startingPositions.add(new Tile(7, 2, bishopB));
        startingPositions.add(new Tile(7, 3,queenB));
        startingPositions.add(new Tile(7, 4, kingB));
        startingPositions.add(new Tile(7, 5,bishopB));
        startingPositions.add(new Tile(7, 6, knightB));
        startingPositions.add(new Tile(7, 7, rookB));
//        startingPositions.add(new Tile(0, 8, archbishopW));
//        startingPositions.add(new Tile(7, 8, archbishopB));


        for (int i = 0; i < board.getWidth(); i++) {
            startingPositions.add(new Tile(1, i, pawnW));
            startingPositions.add(new Tile(6, i, pawnB));
        }


        List<WinCondition> winConditions = new ArrayList<>();
        WinCondition winCondition = new NormalChessCheckmate();
        winConditions.add(winCondition);

        List<StalemateCondition> stalemateConditions = new ArrayList<>();
        StalemateCondition stalemateCondition = new NormalChessStalemate();
        stalemateConditions.add(stalemateCondition);

        Rules rules = new NormalChessRules(startingPositions, winConditions, stalemateConditions);

        for (Tile tile : rules.getStartingPositions()) {
            board = board.replacePosition(tile);
        }

        Player player1 = new Player(Color.WHITE, "Player 1");
        Player player2 = new Player(Color.BLACK, "Player 2");

        return new Game(board, player1, player2, rules, player1, false, new ChessPieceMover(), new ChessMoveVerifier());
    }


}
